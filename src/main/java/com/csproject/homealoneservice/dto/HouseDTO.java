package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.RentalHouseImageEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.List;

public class HouseDTO {

    private int hid;
    private int mid;
    private String houseName;
    private String houseAddress;
    private String houseProvince;
    private String houseDistrict;
    private Integer houseZipcode;
    private String houseImage;
    private String houseType;
    private Integer houseFloors;
    private Integer houseBedroom;
    private Integer houseBathroom;
    private Integer houseLivingroom;
    private Integer houseKitchen;
    private String houseArea;
    private String houseLatitude;
    private String houseLongitude;
    private String houseElectric;
    private String houseWater;
    private Integer houseRent;
    private Integer houseDeposit;
    private Integer houseInsurance;
    private int houseStatus;
    private List<RentalHouseImageEntity> houseImageList;



    public HouseDTO(int hid, int mid, String houseName, String houseAddress, String houseProvince, String houseDistrict, Integer houseZipcode, String houseImage, String houseType, Integer houseFloors, Integer houseBedroom, Integer houseBathroom, Integer houseLivingroom, Integer houseKitchen, String houseArea, String houseLatitude, String houseLongitude, String houseElectric, String houseWater, Integer houseRent, Integer houseDeposit, Integer houseInsurance, int houseStatus, List<RentalHouseImageEntity> houseImageList) {
        this.hid = hid;
        this.mid = mid;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseProvince = houseProvince;
        this.houseDistrict = houseDistrict;
        this.houseZipcode = houseZipcode;
        this.houseImage = houseImage;
        this.houseType = houseType;
        this.houseFloors = houseFloors;
        this.houseBedroom = houseBedroom;
        this.houseBathroom = houseBathroom;
        this.houseLivingroom = houseLivingroom;
        this.houseKitchen = houseKitchen;
        this.houseArea = houseArea;
        this.houseLatitude = houseLatitude;
        this.houseLongitude = houseLongitude;
        this.houseElectric = houseElectric;
        this.houseWater = houseWater;
        this.houseRent = houseRent;
        this.houseDeposit = houseDeposit;
        this.houseInsurance = houseInsurance;
        this.houseStatus = houseStatus;
        this.houseImageList = houseImageList;
    }

    public HouseDTO(int hid, int mid, String houseName, String houseAddress, String houseProvince, String houseDistrict, Integer houseZipcode, String houseImage, String houseType, Integer houseFloors, Integer houseBedroom, Integer houseBathroom, Integer houseLivingroom, Integer houseKitchen, String houseArea, String houseLatitude, String houseLongitude, String houseElectric, String houseWater, Integer houseRent, Integer houseDeposit, Integer houseInsurance, int houseStatus) {
        this.hid = hid;
        this.mid = mid;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseProvince = houseProvince;
        this.houseDistrict = houseDistrict;
        this.houseZipcode = houseZipcode;
        this.houseImage = houseImage;
        this.houseType = houseType;
        this.houseFloors = houseFloors;
        this.houseBedroom = houseBedroom;
        this.houseBathroom = houseBathroom;
        this.houseLivingroom = houseLivingroom;
        this.houseKitchen = houseKitchen;
        this.houseArea = houseArea;
        this.houseLatitude = houseLatitude;
        this.houseLongitude = houseLongitude;
        this.houseElectric = houseElectric;
        this.houseWater = houseWater;
        this.houseRent = houseRent;
        this.houseDeposit = houseDeposit;
        this.houseInsurance = houseInsurance;
        this.houseStatus = houseStatus;
    }

    public List<RentalHouseImageEntity> getHouseImageList() {
        return houseImageList;
    }

    public void setHouseImageList(List<RentalHouseImageEntity> houseImageList) {
        this.houseImageList = houseImageList;
    }

    public HouseDTO() {
    }

//    public HouseDTO(int hid, int mid, String houseName, String houseAddress, String houseProvince, String houseDistrict, Integer houseZipcode, String houseImage, String houseType, Integer houseFloors, Integer houseBedroom, Integer houseBathroom, Integer houseLivingroom, Integer houseKitchen, String houseArea, String houseLatitude, String houseLongitude, String houseElectric, String houseWater, Integer houseRent, Integer houseDeposit, Integer houseInsurance, int houseStatus) {
//        this.hid = hid;
//        this.mid = mid;
//        this.houseName = houseName;
//        this.houseAddress = houseAddress;
//        this.houseProvince = houseProvince;
//        this.houseDistrict = houseDistrict;
//        this.houseZipcode = houseZipcode;
//        this.houseImage = houseImage;
//        this.houseType = houseType;
//        this.houseFloors = houseFloors;
//        this.houseBedroom = houseBedroom;
//        this.houseBathroom = houseBathroom;
//        this.houseLivingroom = houseLivingroom;
//        this.houseKitchen = houseKitchen;
//        this.houseArea = houseArea;
//        this.houseLatitude = houseLatitude;
//        this.houseLongitude = houseLongitude;
//        this.houseElectric = houseElectric;
//        this.houseWater = houseWater;
//        this.houseRent = houseRent;
//        this.houseDeposit = houseDeposit;
//        this.houseInsurance = houseInsurance;
//        this.houseStatus = houseStatus;
//    }

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
}
