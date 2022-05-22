package com.csproject.homealoneservice.dto;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;

import java.sql.Timestamp;

public class PreReviewDTO {
    HouseEntity house;
    RentingHouseEntity rentingHouse;

    public PreReviewDTO() {
    }

    public PreReviewDTO(HouseEntity house, RentingHouseEntity rentingHouse) {
        this.house = house;
        this.rentingHouse = rentingHouse;
    }

    public HouseEntity getHouse() {
        return house;
    }

    public void setHouse(HouseEntity house) {
        this.house = house;
    }

    public RentingHouseEntity getRentingHouse() {
        return rentingHouse;
    }

    public void setRentingHouse(RentingHouseEntity rentingHouse) {
        this.rentingHouse = rentingHouse;
    }
}
