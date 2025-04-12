package models;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LOAN_TXN {

    @JsonProperty("invstr_loan_nbr")
    private Integer invstr_loan_nbr;

    @JsonProperty("srvcr_loan_nbr")
    private Integer srvcr_loan_nbr;

    @JsonProperty("txn_seq_nbr")
    private Integer txn_seq_nbr;

    @JsonProperty("prin_pymt_amt")
    private double prin_pymt_amt;

    @JsonProperty("int_pymt_amt")
    private double int_pymt_amt;

    @JsonProperty("txn_dt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date txn_dt;

    @JsonProperty("next_pymt_due_dt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date next_pymt_due_dt;

    @JsonProperty("crtl_amt")
    private double crtl_amt;

    @JsonProperty("int_only_pymt_amt")
    private double int_only_pymt_amt;

    @JsonProperty("lqdtn_pymt_amt")
    private double lqdtn_pymt_amt;

    @JsonProperty("prin_bal_aftr_pymt")
    private double prin_bal_aftr_pymt;

    @JsonProperty("created_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date createdTimestamp;

    @JsonCreator
    public LOAN_TXN() {
    }

    public Integer getInvstr_loan_nbr() {
        return invstr_loan_nbr;
    }

    public void setInvstr_loan_nbr(Integer invstr_loan_nbr) {
        this.invstr_loan_nbr = invstr_loan_nbr;
    }

    public Integer getSrvcr_loan_nbr() {
        return srvcr_loan_nbr;
    }

    public void setSrvcr_loan_nbr(Integer srvcr_loan_nbr) {
        this.srvcr_loan_nbr = srvcr_loan_nbr;
    }

    public double getPrin_pymt_amt() {
        return prin_pymt_amt;
    }

    public void setPrin_pymt_amt(double prin_pymt_amt) {
        this.prin_pymt_amt = prin_pymt_amt;
    }

    public double getInt_pymt_amt() {
        return int_pymt_amt;
    }

    public void setInt_pymt_amt(double int_pymt_amt) {
        this.int_pymt_amt = int_pymt_amt;
    }

    public Date getTxn_dt() {
        return txn_dt;
    }

    public void setTxn_dt(Date txn_dt) {
        this.txn_dt = txn_dt;
    }

    public Date getNext_pymt_due_dt() {
        return next_pymt_due_dt;
    }

    public void setNext_pymt_due_dt(Date next_pymt_due_dt) {
        this.next_pymt_due_dt = next_pymt_due_dt;
    }

    public double getCrtl_amt() {
        return crtl_amt;
    }

    public void setCrtl_amt(double crtl_amt) {
        this.crtl_amt = crtl_amt;
    }

    public double getInt_only_pymt_amt() {
        return int_only_pymt_amt;
    }

    public void setInt_only_pymt_amt(double int_only_pymt_amt) {
        this.int_only_pymt_amt = int_only_pymt_amt;
    }

    public double getLqdtn_pymt_amt() {
        return lqdtn_pymt_amt;
    }

    public void setLqdtn_pymt_amt(double lqdtn_pymt_amt) {
        this.lqdtn_pymt_amt = lqdtn_pymt_amt;
    }

    public double getPrin_bal_aftr_pymt() {
        return prin_bal_aftr_pymt;
    }

    public void setPrin_bal_aftr_pymt(double prin_bal_aftr_pymt) {
        this.prin_bal_aftr_pymt = prin_bal_aftr_pymt;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Integer getTxn_seq_nbr() {
        return txn_seq_nbr;
    }

    public void setTxn_seq_nbr(Integer txn_seq_nbr) {
        this.txn_seq_nbr = txn_seq_nbr;
    }

    @Override
    public String toString() {
        return "LOAN_TXN{" +
                "invstr_loan_nbr=" + invstr_loan_nbr +
                ", srvcr_loan_nbr=" + srvcr_loan_nbr +
                ", prin_pymt_amt=" + prin_pymt_amt +
                ", int_pymt_amt=" + int_pymt_amt +
                ", txn_dt=" + txn_dt +
                ", next_pymt_due_dt=" + next_pymt_due_dt +
                ", crtl_amt=" + crtl_amt +
                ", int_only_pymt_amt=" + int_only_pymt_amt +
                ", lqdtn_pymt_amt=" + lqdtn_pymt_amt +
                ", prin_bal_aftr_pymt=" + prin_bal_aftr_pymt +
                '}';
    }
}
