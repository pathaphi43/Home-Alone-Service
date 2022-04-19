package com.csproject.homealoneservice.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "house", schema = "comsci_homealone", catalog = "")
public class HouseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "hid")
    private int hid;
    @Basic
    @Column(name = "mid")
    private int mid;
    @Basic
    @Column(name = "house_name")
    private String houseName;
    @Basic
    @Column(name = "house_address")
    private String houseAddress;
    @Basic
    @Column(name = "house_province")
    private String houseProvince;
    @Basic
    @Column(name = "house_district")
    private String houseDistrict;
    @Basic
    @Column(name = "house_zipcode")
    private Integer houseZipcode;
    @Basic
    @Column(name = "house_image")
    private String houseImage;
    @Basic
    @Column(name = "house_type")
    private String houseType;
    @Basic
    @Column(name = "house_floors")
    private Integer houseFloors;
    @Basic
    @Column(name = "house_bedroom")
    private Integer houseBedroom;
    @Basic
    @Column(name = "house_bathroom")
    private Integer houseBathroom;
    @Basic
    @Column(name = "house_livingroom")
    private Integer houseLivingroom;
    @Basic
    @Column(name = "house_kitchen")
    private Integer houseKitchen;
    @Basic
    @Column(name = "house_area")
    private String houseArea;
    @Basic
    @Column(name = "house_latitude")
    private String houseLatitude;
    @Basic
    @Column(name = "house_longitude")
    private String houseLongitude;
    @Basic
    @Column(name = "house_electric")
    private String houseElectric;
    @Basic
    @Column(name = "house_water")
    private String houseWater;
    @Basic
    @Column(name = "house_rent")
    private Integer houseRent;
    @Basic
    @Column(name = "house_deposit")
    private Integer houseDeposit;
    @Basic
    @Column(name = "house_insurance")
    private Integer houseInsurance;
    @Basic
    @Column(name = "house_status")
    private int houseStatus;


    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

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

    public Integer getHouseZipcode() {
        return houseZipcode;
    }

    public void setHouseZipcode(Integer houseZipcode) {
        this.houseZipcode = houseZipcode;
    }

    public String getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(String houseImage) {
        this.houseImage = houseImage;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public Integer getHouseFloors() {
        return houseFloors;
    }

    public void setHouseFloors(Integer houseFloors) {
        this.houseFloors = houseFloors;
    }

    public Integer getHouseBedroom() {
        return houseBedroom;
    }

    public void setHouseBedroom(Integer houseBedroom) {
        this.houseBedroom = houseBedroom;
    }

    public Integer getHouseBathroom() {
        return houseBathroom;
    }

    public void setHouseBathroom(Integer houseBathroom) {
        this.houseBathroom = houseBathroom;
    }

    public Integer getHouseLivingroom() {
        return houseLivingroom;
    }

    public void setHouseLivingroom(Integer houseLivingroom) {
        this.houseLivingroom = houseLivingroom;
    }

    public Integer getHouseKitchen() {
        return houseKitchen;
    }

    public void setHouseKitchen(Integer houseKitchen) {
        this.houseKitchen = houseKitchen;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseEntity that = (HouseEntity) o;

        if (hid != that.hid) return false;
        if (mid != that.mid) return false;
        if (houseStatus != that.houseStatus) return false;
        if (houseName != null ? !houseName.equals(that.houseName) : that.houseName != null) return false;
        if (houseAddress != null ? !houseAddress.equals(that.houseAddress) : that.houseAddress != null) return false;
        if (houseProvince != null ? !houseProvince.equals(that.houseProvince) : that.houseProvince != null)
            return false;
        if (houseDistrict != null ? !houseDistrict.equals(that.houseDistrict) : that.houseDistrict != null)
            return false;
        if (houseZipcode != null ? !houseZipcode.equals(that.houseZipcode) : that.houseZipcode != null) return false;
        if (houseImage != null ? !houseImage.equals(that.houseImage) : that.houseImage != null) return false;
        if (houseType != null ? !houseType.equals(that.houseType) : that.houseType != null) return false;
        if (houseFloors != null ? !houseFloors.equals(that.houseFloors) : that.houseFloors != null) return false;
        if (houseBedroom != null ? !houseBedroom.equals(that.houseBedroom) : that.houseBedroom != null) return false;
        if (houseBathroom != null ? !houseBathroom.equals(that.houseBathroom) : that.houseBathroom != null)
            return false;
        if (houseLivingroom != null ? !houseLivingroom.equals(that.houseLivingroom) : that.houseLivingroom != null)
            return false;
        if (houseKitchen != null ? !houseKitchen.equals(that.houseKitchen) : that.houseKitchen != null) return false;
        if (houseArea != null ? !houseArea.equals(that.houseArea) : that.houseArea != null) return false;
        if (houseLatitude != null ? !houseLatitude.equals(that.houseLatitude) : that.houseLatitude != null)
            return false;
        if (houseLongitude != null ? !houseLongitude.equals(that.houseLongitude) : that.houseLongitude != null)
            return false;
        if (houseElectric != null ? !houseElectric.equals(that.houseElectric) : that.houseElectric != null)
            return false;
        if (houseWater != null ? !houseWater.equals(that.houseWater) : that.houseWater != null) return false;
        if (houseRent != null ? !houseRent.equals(that.houseRent) : that.houseRent != null) return false;
        if (houseDeposit != null ? !houseDeposit.equals(that.houseDeposit) : that.houseDeposit != null) return false;
        if (houseInsurance != null ? !houseInsurance.equals(that.houseInsurance) : that.houseInsurance != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + mid;
        result = 31 * result + (houseName != null ? houseName.hashCode() : 0);
        result = 31 * result + (houseAddress != null ? houseAddress.hashCode() : 0);
        result = 31 * result + (houseProvince != null ? houseProvince.hashCode() : 0);
        result = 31 * result + (houseDistrict != null ? houseDistrict.hashCode() : 0);
        result = 31 * result + (houseZipcode != null ? houseZipcode.hashCode() : 0);
        result = 31 * result + (houseImage != null ? houseImage.hashCode() : 0);
        result = 31 * result + (houseType != null ? houseType.hashCode() : 0);
        result = 31 * result + (houseFloors != null ? houseFloors.hashCode() : 0);
        result = 31 * result + (houseBedroom != null ? houseBedroom.hashCode() : 0);
        result = 31 * result + (houseBathroom != null ? houseBathroom.hashCode() : 0);
        result = 31 * result + (houseLivingroom != null ? houseLivingroom.hashCode() : 0);
        result = 31 * result + (houseKitchen != null ? houseKitchen.hashCode() : 0);
        result = 31 * result + (houseArea != null ? houseArea.hashCode() : 0);
        result = 31 * result + (houseLatitude != null ? houseLatitude.hashCode() : 0);
        result = 31 * result + (houseLongitude != null ? houseLongitude.hashCode() : 0);
        result = 31 * result + (houseElectric != null ? houseElectric.hashCode() : 0);
        result = 31 * result + (houseWater != null ? houseWater.hashCode() : 0);
        result = 31 * result + (houseRent != null ? houseRent.hashCode() : 0);
        result = 31 * result + (houseDeposit != null ? houseDeposit.hashCode() : 0);
        result = 31 * result + (houseInsurance != null ? houseInsurance.hashCode() : 0);
        result = 31 * result + houseStatus;
        return result;
    }
}
