package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.TenantEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
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

//    private TenantEntity tenant;
//
//    private HouseEntity house;

    private int mid;
    private String houseName;
    private String houseAddress;
    private String houseProvince;
    private String houseDistrict;
    private String houseLatitude;
    private String houseLongitude;
    private String houseElectric;
    private String houseWater;
    private Integer houseRent;
    private Integer houseDeposit;
    private Integer houseInsurance;
    private int houseStatus;

    private String tenantUsername;

    private String tenantFirstname;

    private String tenantLastname;

    private String tenantPhone;

    private String tenantAddress;

    private String tenantProvince;

    private String tenantDistrict;

    private String tenantImage;

    private int tenantStatus;

    public RentDTO() {
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
//
//    public TenantEntity getTenant() {
//        return tenant;
//    }
//
//    public void setTenant(TenantEntity tenant) {
//        this.tenant = tenant;
//    }
//
//    public HouseEntity getHouse() {
//        return house;
//    }
//
//    public void setHouse(HouseEntity house) {
//        this.house = house;
//    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseProvince() {
        return houseProvince;
    }

    public void setHouseProvince(String houseProvince) {
        this.houseProvince = houseProvince;
    }

    public String getHouseDistrict() {
        return houseDistrict;
    }

    public void setHouseDistrict(String houseDistrict) {
        this.houseDistrict = houseDistrict;
    }

    public String getHouseLatitude() {
        return houseLatitude;
    }

    public void setHouseLatitude(String houseLatitude) {
        this.houseLatitude = houseLatitude;
    }

    public String getHouseLongitude() {
        return houseLongitude;
    }

    public void setHouseLongitude(String houseLongitude) {
        this.houseLongitude = houseLongitude;
    }

    public String getHouseElectric() {
        return houseElectric;
    }

    public void setHouseElectric(String houseElectric) {
        this.houseElectric = houseElectric;
    }

    public String getHouseWater() {
        return houseWater;
    }

    public void setHouseWater(String houseWater) {
        this.houseWater = houseWater;
    }

    public Integer getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(Integer houseRent) {
        this.houseRent = houseRent;
    }

    public Integer getHouseDeposit() {
        return houseDeposit;
    }

    public void setHouseDeposit(Integer houseDeposit) {
        this.houseDeposit = houseDeposit;
    }

    public Integer getHouseInsurance() {
        return houseInsurance;
    }

    public void setHouseInsurance(Integer houseInsurance) {
        this.houseInsurance = houseInsurance;
    }

    public int getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(int houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getTenantUsername() {
        return tenantUsername;
    }

    public void setTenantUsername(String tenantUsername) {
        this.tenantUsername = tenantUsername;
    }

    public String getTenantFirstname() {
        return tenantFirstname;
    }

    public void setTenantFirstname(String tenantFirstname) {
        this.tenantFirstname = tenantFirstname;
    }

    public String getTenantLastname() {
        return tenantLastname;
    }

    public void setTenantLastname(String tenantLastname) {
        this.tenantLastname = tenantLastname;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }

    public String getTenantAddress() {
        return tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress;
    }

    public String getTenantProvince() {
        return tenantProvince;
    }

    public void setTenantProvince(String tenantProvince) {
        this.tenantProvince = tenantProvince;
    }

    public String getTenantDistrict() {
        return tenantDistrict;
    }

    public void setTenantDistrict(String tenantDistrict) {
        this.tenantDistrict = tenantDistrict;
    }

    public String getTenantImage() {
        return tenantImage;
    }

    public void setTenantImage(String tenantImage) {
        this.tenantImage = tenantImage;
    }

    public int getTenantStatus() {
        return tenantStatus;
    }

    public void setTenantStatus(int tenantStatus) {
        this.tenantStatus = tenantStatus;
    }
}
