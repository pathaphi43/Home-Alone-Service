package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.List;

public class PaymentDTO {

    //    private int id;
//
//    private Integer rid;
//
//    private Timestamp installment;
//
//    private int payHouseAmount;
//
//    private Timestamp payHouseDate;
//
//    private Timestamp payHouseEnd;
//
//    private String payHouseImg;
//
//    private int payHouseStatus;
//
//    private Timestamp payElecInmonth;
//
//    private String payElecAmount;
//
//    private Timestamp payElecDate;
//
//    private Timestamp payElecEnd;
//
//    private String payElecImg;
//
//    private int payElecStatus;
//
//    private Timestamp payWaterInmonth;
//
//    private String payWaterAmount;
//
//    private Timestamp payWaterDate;
//
//    private Timestamp payWaterEnd;
//
//    private String payWaterImg;
//
//    private int payWaterStatus;
    private ManagerEntity manager;

    private HouseEntity house;

    private TenantEntity tenant;

    private RentingHouseEntity rentingHouse;

    private List<PaymentEntity> payments;

    private PaymentEntity payment;

    public PaymentDTO() {
    }

    public PaymentDTO(ManagerEntity manager, HouseEntity house, TenantEntity tenant, RentingHouseEntity rentingHouse, List<PaymentEntity> payments) {
        this.manager = manager;
        this.house = house;
        this.tenant = tenant;
        this.rentingHouse = rentingHouse;
        this.payments = payments;
    }

    public PaymentDTO(HouseEntity house, TenantEntity tenant, PaymentEntity payment) {
        this.house = house;
        this.tenant = tenant;
        this.payment = payment;
    }

    public PaymentDTO(HouseEntity house, TenantEntity tenant, List<PaymentEntity> payments) {
        this.house = house;
        this.tenant = tenant;
        this.payments = payments;
    }

    public ManagerEntity getManager() {
        return manager;
    }

    public void setManager(ManagerEntity manager) {
        this.manager = manager;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    public TenantEntity getTenant() {
        return tenant;
    }

    public void setTenant(TenantEntity tenant) {
        this.tenant = tenant;
    }

    public RentingHouseEntity getRentingHouse() {
        return rentingHouse;
    }

    public void setRentingHouse(RentingHouseEntity rentingHouse) {
        this.rentingHouse = rentingHouse;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentEntity> payments) {
        this.payments = payments;
    }
}
