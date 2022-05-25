package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;

import java.util.List;

public class PaymentSummaryReportDTO {

    private String dateFrom;
    private String dateTo;
    private String managerFirstname;
    private String managerLastname;
    private String dateCreate;
    private List<DataListDTO> payments;

    public PaymentSummaryReportDTO() {
    }

    public PaymentSummaryReportDTO(String dateFrom, String dateTo, String managerFirstname, String managerLastname, String dateCreate, List<DataListDTO> payments) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.managerFirstname = managerFirstname;
        this.managerLastname = managerLastname;
        this.dateCreate = dateCreate;
        this.payments = payments;
    }

    public List<DataListDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<DataListDTO> payments) {
        this.payments = payments;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getManagerFirstname() {
        return managerFirstname;
    }

    public void setManagerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}

