package models;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LOAN {
    @JsonProperty("invstr_loan_nbr")
    private Integer invstr_loan_nbr;

    @JsonProperty("srvcr_loan_nbr")
    private Integer srvcr_loan_nbr;

    @JsonProperty("upb_amt")
    private double upb_amt;

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

    @JsonProperty("created_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date createdTimestamp;

    @JsonCreator
    public LOAN() {
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

    public double getUpb_amt() {
        return upb_amt;
    }

    public void setUpb_amt(double upb_amt) {
        this.upb_amt = upb_amt;
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

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
}
