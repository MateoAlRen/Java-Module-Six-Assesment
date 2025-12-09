package com.modulesixassesment.assesment.domain.model;

import com.modulesixassesment.assesment.domain.enums.AffiliatedStatus;

import java.time.LocalDate;

public class Affiliated {
    private Long affiliatedId;
    private String document;
    private String name;
    private double salary;
    private LocalDate dateAffiliated;
    private AffiliatedStatus status;

    public Affiliated(Long affiliatedId, String document, String name, LocalDate dateAffiliated, double salary, AffiliatedStatus status) {
        this.affiliatedId = affiliatedId;
        this.document = document;
        this.name = name;
        this.dateAffiliated = dateAffiliated;
        this.salary = salary;
        this.status = status;
    }

    public Long getAffiliatedId() {
        return affiliatedId;
    }

    public void setAffiliatedId(Long affiliatedId) {
        this.affiliatedId = affiliatedId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDateAffiliated() {
        return dateAffiliated;
    }

    public void setDateAffiliated(LocalDate dateAffiliated) {
        this.dateAffiliated = dateAffiliated;
    }

    public AffiliatedStatus getStatus() {
        return status;
    }

    public void setStatus(AffiliatedStatus status) {
        this.status = status;
    }
}
