package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;

import java.util.List;

public class PaymentSummaryReportDTO {

    private String houseName;
    private String paymentMonth;
    private String summaryAmount;

    public PaymentSummaryReportDTO() {
    }

    public PaymentSummaryReportDTO(String houseName, String paymentMonth, String summaryAmount) {
        this.houseName = houseName;
        this.paymentMonth = paymentMonth;
        this.summaryAmount = summaryAmount;
    }

    public String getSummaryAmount() {
        return summaryAmount;
    }

    public void setSummaryAmount(String summaryAmount) {
        this.summaryAmount = summaryAmount;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }


    //    private String managerLastname;
//    private String dateCreate;
//    private List<DataListDTO> payments;
//
//    public PaymentSummaryReportDTO() {
//    }
//
//    public PaymentSummaryReportDTO(String dateFrom, String dateTo, String managerFirstname, String managerLastname, String dateCreate, List<DataListDTO> payments) {
//        this.dateFrom = dateFrom;
//        this.dateTo = dateTo;
//        this.managerFirstname = managerFirstname;
//        this.managerLastname = managerLastname;
//        this.dateCreate = dateCreate;
//        this.payments = payments;
//    }
//
//    public List<DataListDTO> getPayments() {
//        return payments;
//    }
//
//    public void setPayments(List<DataListDTO> payments) {
//        this.payments = payments;
//    }
//
//    public String getDateFrom() {
//        return dateFrom;
//    }
//
//    public void setDateFrom(String dateFrom) {
//        this.dateFrom = dateFrom;
//    }
//
//    public String getDateTo() {
//        return dateTo;
//    }
//
//    public void setDateTo(String dateTo) {
//        this.dateTo = dateTo;
//    }
//
//    public String getManagerFirstname() {
//        return managerFirstname;
//    }
//
//    public void setManagerFirstname(String managerFirstname) {
//        this.managerFirstname = managerFirstname;
//    }
//
//    public String getManagerLastname() {
//        return managerLastname;
//    }
//
//    public void setManagerLastname(String managerLastname) {
//        this.managerLastname = managerLastname;
//    }
//
//    public String getDateCreate() {
//        return dateCreate;
//    }
//
//    public void setDateCreate(String dateCreate) {
//        this.dateCreate = dateCreate;
//    }
}

