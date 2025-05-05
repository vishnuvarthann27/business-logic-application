package com.flink;

import models.LOAN;
import models.LOAN_TXN;
import models.Reporting_Record;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.formats.json.JsonDeserializationSchema;
import org.apache.flink.formats.json.JsonSerializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.util.Collector;

import java.io.InputStream;
import java.util.Properties;


public class BusinessLogicJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties consumerConfig = new Properties();

        try (InputStream stream = BusinessLogicJob.class.getClassLoader().getResourceAsStream("consumer.properties")) {
            consumerConfig.load(stream);
        }

        Properties producerConfig = new Properties();
        try (InputStream stream = BusinessLogicJob.class.getClassLoader().getResourceAsStream("producer.properties")) {
            producerConfig.load(stream);
        }

        KafkaSource<LOAN> loanSource =  KafkaSource.<LOAN>builder()
                                                        .setProperties(consumerConfig)
                                                        .setTopics("LOAN")
                                                        .setStartingOffsets(OffsetsInitializer.latest())
                                                        .setValueOnlyDeserializer(new JsonDeserializationSchema<>(LOAN.class))
                                                        .build();

        KafkaSource<LOAN_TXN> loanTxnSource =  KafkaSource.<LOAN_TXN>builder()
                                                        .setProperties(consumerConfig)
                                                        .setTopics("LOAN_TXN")
                                                        .setStartingOffsets(OffsetsInitializer.latest())
                                                        .setValueOnlyDeserializer(new JsonDeserializationSchema<>(LOAN_TXN.class))
                                                        .build();

        DataStream<LOAN> loanStream = env.fromSource(loanSource, WatermarkStrategy.noWatermarks(), "loan_source");
        DataStream<LOAN_TXN> loanTxnStream = env.fromSource(loanTxnSource, WatermarkStrategy.noWatermarks(), "loan_txn_source");

        DataStream<LOAN> loanStreamWithTs = loanStream.assignTimestampsAndWatermarks(
                WatermarkStrategy.<LOAN>forMonotonousTimestamps()
                        .withTimestampAssigner((loan, timestamp) -> loan.getCreatedTimestamp().getTime()) // Ensure your LOAN class has a timestamp field
        );

        DataStream<LOAN_TXN> loanTxnStreamWithTs = loanTxnStream.assignTimestampsAndWatermarks(
                WatermarkStrategy.<LOAN_TXN>forMonotonousTimestamps()
                        .withTimestampAssigner((loanTxn, timestamp) -> loanTxn.getCreatedTimestamp().getTime()) // Ensure your LOAN_TXN class has a timestamp field
        );

        KafkaRecordSerializationSchema<Reporting_Record> llrSerializer = KafkaRecordSerializationSchema.<Reporting_Record>builder()
                .setTopic("LLR")
                .setKeySerializationSchema((llr) -> String.valueOf(llr.getInvstr_loan_nbr()).getBytes())
                .setValueSerializationSchema(new JsonSerializationSchema<Reporting_Record>(
                        () -> new ObjectMapper().registerModule(new JavaTimeModule())
                ))
                .build();

        KafkaSink<Reporting_Record> llrSink = KafkaSink.<Reporting_Record>builder()
                .setKafkaProducerConfig(producerConfig)
                .setRecordSerializer(llrSerializer)
                .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build();

        System.out.print(loanStreamWithTs);
        System.out.print(loanTxnStreamWithTs);

        defineWorkflow(loanStreamWithTs, loanTxnStreamWithTs)
                .sinkTo(llrSink)
                .name("llr_sink");

        env.setRestartStrategy(RestartStrategies.exponentialDelayRestart(
                Time.seconds(5),
                Time.minutes(1),
                2.0,
                Time.minutes(5),
                0.1

        ));

        env.execute("BUSINESS_LOGIC");
    }

    public static DataStream<Reporting_Record> defineWorkflow(DataStream<LOAN> loanSource, DataStream<LOAN_TXN> loanTxnSource) {

        DataStream<LOAN_TXN> aggregatedLoanTxn = loanTxnSource
                .keyBy((KeySelector<LOAN_TXN, Integer>) LOAN_TXN::getInvstr_loan_nbr)
                .window(TumblingEventTimeWindows.of(org.apache.flink.streaming.api.windowing.time.Time.minutes(2)))
                .aggregate(new LoanTxnAggregator());

        return loanSource
                .join(aggregatedLoanTxn)
                .where(LOAN::getInvstr_loan_nbr)
                .equalTo(LOAN_TXN::getInvstr_loan_nbr)
                .window(TumblingEventTimeWindows.of(org.apache.flink.streaming.api.windowing.time.Time.minutes(2)))
                .apply(new JoinFunction<LOAN, LOAN_TXN, Reporting_Record>() {
                    @Override
                    public Reporting_Record join(LOAN loan, LOAN_TXN aggregatedLoanTxn) {
                        return new Reporting_Record(loan, aggregatedLoanTxn);
                    }
                });

    }
}

