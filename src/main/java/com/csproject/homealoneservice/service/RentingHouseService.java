package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.enumeration.HouseEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    RentingHouseRepository rentingHouseRepository;

    private static Date queryDate;
    private String formatDate = "yyyyMMdd";
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ZoneId zone = ZoneId.of("Asia/Bangkok");

    public RentingHouseEntity rentHouse(Integer hid,Integer tid){
        RentingHouseEntity rentingHouse = new RentingHouseEntity();
        rentingHouse.setHid(hid);
        rentingHouse.setTid(tid);
        logger.info("Time Zone is "+zone);
        rentingHouse.setRentingBook(Timestamp.from(ZonedDateTime.now(zone).toInstant()));
        logger.info(Timestamp.from(ZonedDateTime.now(zone).toInstant()));
        rentingHouse.setRentingStatus(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
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
