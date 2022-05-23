package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;

import java.util.List;

public class PaymentSummaryDTO {
    private HouseEntity house;
    private List<PaymentEntity> payments;

    public PaymentSummaryDTO() {
    }

    public PaymentSummaryDTO(HouseEntity house, List<PaymentEntity> payments) {
        this.house = house;
        this.payments = payments;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }
}
