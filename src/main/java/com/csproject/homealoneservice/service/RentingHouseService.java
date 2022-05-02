package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.enumeration.HouseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class RentingHouseService {

    @Autowired
    RentingHouseRepository rentingHouseRepository;

    private static Date queryDate;
    private String formatDate = "yyyyMMdd";
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public RentingHouseEntity rentHouse(Integer hid,Integer tid){
        RentingHouseEntity rentingHouse = new RentingHouseEntity();
        rentingHouse.setHid(hid);
        rentingHouse.setTid(tid);
        System.out.println(Timestamp.from(ZonedDateTime.now(ZoneId.of("GMT+7")).toInstant()));
        System.out.println(sdf3.format(Timestamp.from(ZonedDateTime.now().toInstant())));
        rentingHouse.setRentingBook(Timestamp.from(ZonedDateTime.now().toInstant()));
//        new SimpleDateFormat(this.getFormatDate()).format(new Date())
        rentingHouse.setRentingStatus(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        System.out.println(rentingHouse);
        return rentingHouseRepository.save(rentingHouse);
    }

    public void cancelRentHouse(Integer id){
    }

    public String getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(String formatDate) {
        this.formatDate = formatDate;
    }
    public static Date getQueryDate() {
        return queryDate;
    }
}
