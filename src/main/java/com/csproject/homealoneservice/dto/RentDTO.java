package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.TenantEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.sql.Timestamp;

public class RentDTO {
    private int rid;

    private int hid;

    private int tid;

    private Timestamp rentingBook;

    private Timestamp rentingCheckIn;

    private Timestamp rentingCheckOut;

    private String rentingImage;

    private int rentingStatus;

    private TenantEntity tenant;

    private HouseEntity house;

    public RentDTO() {
    }

    public RentDTO(int rid, int hid, int tid, Timestamp rentingBook, Timestamp rentingCheckIn, Timestamp rentingCheckOut, String rentingImage, int rentingStatus, TenantEntity tenant, HouseEntity house) {
        this.rid = rid;
        this.hid = hid;
        this.tid = tid;
        this.rentingBook = rentingBook;
        this.rentingCheckIn = rentingCheckIn;
        this.rentingCheckOut = rentingCheckOut;
        this.rentingImage = rentingImage;
        this.rentingStatus = rentingStatus;
        this.tenant = tenant;
        this.house = house;
    }

    public RentDTO(int hid, int tid) {
        this.hid = hid;
        this.tid = tid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Timestamp getRentingBook() {
        return rentingBook;
    }

    public void setRentingBook(Timestamp rentingBook) {
        this.rentingBook = rentingBook;
    }

    public Timestamp getRentingCheckIn() {
        return rentingCheckIn;
    }

    public void setRentingCheckIn(Timestamp rentingCheckIn) {
        this.rentingCheckIn = rentingCheckIn;
    }

    public Timestamp getRentingCheckOut() {
        return rentingCheckOut;
    }

    public void setRentingCheckOut(Timestamp rentingCheckOut) {
        this.rentingCheckOut = rentingCheckOut;
    }

    public String getRentingImage() {
        return rentingImage;
    }

    public void setRentingImage(String rentingImage) {
        this.rentingImage = rentingImage;
    }

    public int getRentingStatus() {
        return rentingStatus;
    }

    public void setRentingStatus(int rentingStatus) {
        this.rentingStatus = rentingStatus;
    }

    public TenantEntity getTenant() {
        return tenant;
    }

    public void setTenant(TenantEntity tenant) {
        this.tenant = tenant;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }
}
