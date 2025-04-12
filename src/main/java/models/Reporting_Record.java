package models;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reporting_Record {
    @JsonProperty("invstr_loan_nbr")
    private Integer invstr_loan_nbr;

    @JsonProperty("srvcr_loan_nbr")
    private Integer srvcr_loan_nbr;

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

    @JsonProperty("end_upb_amt")
    private double end_upb_amt;

    @JsonProperty("opening_upb_amt")
    private double opening_upb_amt;

    @JsonProperty("original_prin_bal")
    private double original_prin_bal;

    @JsonProperty("ddlpi_dt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ddlpi_dt;

    @JsonProperty("first_payment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date first_payment_date;

    @JsonProperty("loan_mtr_dt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date loan_mtr_dt;

    @JsonProperty("invstr_cd")
    private String invstr_cd;

    @JsonProperty("issur_nbr")
    private String issur_nbr;


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

    public double getEnd_upb_amt() {
        return end_upb_amt;
    }

    public void setEnd_upb_amt(double end_upb_amt) {
        this.end_upb_amt = end_upb_amt;
    }

    public double getOpening_upb_amt() {
        return opening_upb_amt;
    }

    public void setOpening_upb_amt(double opening_upb_amt) {
        this.opening_upb_amt = opening_upb_amt;
    }

    public double getOriginal_prin_bal() {
        return original_prin_bal;
    }

    public void setOriginal_prin_bal(double original_prin_bal) {
        this.original_prin_bal = original_prin_bal;
    }

    public Date getDdlpi_dt() {
        return ddlpi_dt;
    }

    public void setDdlpi_dt(Date ddlpi_dt) {
        this.ddlpi_dt = ddlpi_dt;
    }

    public Date getFirst_payment_date() {
        return first_payment_date;
    }

    public void setFirst_payment_date(Date first_payment_date) {
        this.first_payment_date = first_payment_date;
    }

    public Date getLoan_mtr_dt() {
        return loan_mtr_dt;
    }

    public void setLoan_mtr_dt(Date loan_mtr_dt) {
        this.loan_mtr_dt = loan_mtr_dt;
    }

    public String getInvstr_cd() {
        return invstr_cd;
    }

    public void setInvstr_cd(String invstr_cd) {
        this.invstr_cd = invstr_cd;
    }

    public String getIssur_nbr() {
        return issur_nbr;
    }

    public void setIssur_nbr(String issur_nbr) {
        this.issur_nbr = issur_nbr;
    }

    @JsonCreator
    public Reporting_Record() {
    }

    public Reporting_Record(LOAN loan, LOAN_TXN loanTxn){
        this.invstr_loan_nbr = loan.getInvstr_loan_nbr();
        this.srvcr_loan_nbr = loan.getSrvcr_loan_nbr();
        this.prin_pymt_amt = loanTxn.getPrin_pymt_amt();
        this.int_pymt_amt = loanTxn.getInt_pymt_amt();
        this.txn_dt = loanTxn.getTxn_dt();
        this.next_pymt_due_dt = loanTxn.getNext_pymt_due_dt();
        this.crtl_amt = loanTxn.getCrtl_amt();
        this.int_only_pymt_amt = loanTxn.getInt_only_pymt_amt();
        this.lqdtn_pymt_amt = loanTxn.getLqdtn_pymt_amt();
        this.end_upb_amt = loanTxn.getPrin_bal_aftr_pymt();
        this.opening_upb_amt = loan.getUpb_amt();
        this.original_prin_bal = loan.getOriginal_prin_bal();
        this.ddlpi_dt = loan.getDdlpi_dt();
        this.first_payment_date = loan.getFirst_payment_date();
        this.loan_mtr_dt = loan.getLoan_mtr_dt();
        this.invstr_cd = loan.getInvstr_cd();
        this.issur_nbr = loan.getIssur_nbr();

        System.out.print(this);
    }
}
