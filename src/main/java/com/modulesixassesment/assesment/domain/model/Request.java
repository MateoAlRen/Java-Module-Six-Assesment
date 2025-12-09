package com.modulesixassesment.assesment.domain.model;

import com.modulesixassesment.assesment.domain.enums.RequestStatus;

import java.time.LocalDate;
import java.util.Locale;

public class Request {
    private Long requestId;
    private String documentAffiliated;
    private double amount;
    private int months;
    private double amountPropose;
    private LocalDate dateRequest;
    private RequestStatus status;
    private RiskEvaluation evaluation;

    public Request() {}

    public Request(Long requestId, String documentAffiliated, double amount, int months, double amountPropose, LocalDate dateRequest, RequestStatus status, RiskEvaluation evaluation) {
        this.requestId = requestId;
        this.documentAffiliated = documentAffiliated;
        this.amount = amount;
        this.months = months;
        this.amountPropose = amountPropose;
        this.dateRequest = dateRequest;
        this.status = status;
        this.evaluation = evaluation;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDocumentAffiliated() {
        return documentAffiliated;
    }

    public void setDocumentAffiliated(String documentAffiliated) {
        this.documentAffiliated = documentAffiliated;
    }

    public double getAmountPropose() {
        return amountPropose;
    }

    public void setAmountPropose(double amountPropose) {
        this.amountPropose = amountPropose;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RiskEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(RiskEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}
