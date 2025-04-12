package com.flink;

import org.apache.flink.api.common.functions.AggregateFunction;
import models.LOAN_TXN;

class LoanTxnAggregator implements AggregateFunction<LOAN_TXN, LOAN_TXN, LOAN_TXN> {

    @Override
    public LOAN_TXN createAccumulator() {
        return new LOAN_TXN();
    }

    @Override
    public LOAN_TXN add(LOAN_TXN txn, LOAN_TXN accumulator) {
        accumulator.setInvstr_loan_nbr(txn.getInvstr_loan_nbr());

        accumulator.setPrin_pymt_amt(accumulator.getPrin_pymt_amt() + txn.getPrin_pymt_amt());
        accumulator.setInt_pymt_amt(accumulator.getInt_pymt_amt() + txn.getInt_pymt_amt());
        accumulator.setCrtl_amt(accumulator.getCrtl_amt() + txn.getCrtl_amt());
        accumulator.setInt_only_pymt_amt(accumulator.getInt_only_pymt_amt() + txn.getInt_only_pymt_amt());
        accumulator.setLqdtn_pymt_amt(accumulator.getLqdtn_pymt_amt() + txn.getLqdtn_pymt_amt());

        if (accumulator.getTxn_seq_nbr() == null || txn.getTxn_seq_nbr() > accumulator.getTxn_seq_nbr()) {
            accumulator.setTxn_seq_nbr(txn.getTxn_seq_nbr());
            accumulator.setTxn_dt(txn.getTxn_dt());
            accumulator.setNext_pymt_due_dt(txn.getNext_pymt_due_dt());
            accumulator.setCreatedTimestamp(txn.getCreatedTimestamp());
            accumulator.setPrin_bal_aftr_pymt(txn.getPrin_bal_aftr_pymt());
        }

        return accumulator;
    }

    @Override
    public LOAN_TXN getResult(LOAN_TXN accumulator) {
        return accumulator;
    }

    @Override
    public LOAN_TXN merge(LOAN_TXN acc1, LOAN_TXN acc2) {
        LOAN_TXN merged = new LOAN_TXN();
        merged.setInvstr_loan_nbr(acc1.getInvstr_loan_nbr());

        merged.setPrin_pymt_amt(acc1.getPrin_pymt_amt() + acc2.getPrin_pymt_amt());
        merged.setInt_pymt_amt(acc1.getInt_pymt_amt() + acc2.getInt_pymt_amt());
        merged.setCrtl_amt(acc1.getCrtl_amt() + acc2.getCrtl_amt());
        merged.setInt_only_pymt_amt(acc1.getInt_only_pymt_amt() + acc2.getInt_only_pymt_amt());
        merged.setLqdtn_pymt_amt(acc1.getLqdtn_pymt_amt() + acc2.getLqdtn_pymt_amt());

        if (acc1.getTxn_seq_nbr() > acc2.getTxn_seq_nbr()) {
            merged.setTxn_seq_nbr(acc1.getTxn_seq_nbr());
            merged.setTxn_dt(acc1.getTxn_dt());
            merged.setNext_pymt_due_dt(acc1.getNext_pymt_due_dt());
            merged.setCreatedTimestamp(acc1.getCreatedTimestamp());
            merged.setPrin_bal_aftr_pymt(acc1.getPrin_bal_aftr_pymt());
        } else {
            merged.setTxn_seq_nbr(acc2.getTxn_seq_nbr());
            merged.setTxn_dt(acc2.getTxn_dt());
            merged.setNext_pymt_due_dt(acc2.getNext_pymt_due_dt());
            merged.setCreatedTimestamp(acc2.getCreatedTimestamp());
            merged.setPrin_bal_aftr_pymt(acc2.getPrin_bal_aftr_pymt());
        }

        return merged;
    }
}

