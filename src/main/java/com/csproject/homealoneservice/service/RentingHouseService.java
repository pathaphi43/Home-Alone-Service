package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
import com.csproject.homealoneservice.enumeration.HouseEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Autowired
    HouseService houseService;

    @Autowired
    TenantService tenantService;

    @Autowired
    FileUpload fileUpload;

    private static Date queryDate;
    private String formatDate = "yyyyMMdd";
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ZoneId zone = ZoneId.of("Asia/Bangkok");
    ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Bangkok"));

    public RentingHouseEntity rentHouse(Integer hid,Integer tid){
        RentingHouseEntity rentingHouse = new RentingHouseEntity();
        rentingHouse.setHid(hid);
        rentingHouse.setTid(tid);
        logger.info(Timestamp.valueOf(zdt.toLocalDateTime()));
        rentingHouse.setRentingBook(Timestamp.valueOf(zdt.toLocalDateTime()));
        rentingHouse.setRentingStatus(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        HouseEntity result = houseService.rentHouse(hid);
        if (result != null)return rentingHouseRepository.save(rentingHouse);
        else  return null;

    }
    public RentingHouseEntity confirmRentHouse(RentingHouseEntity rentBody, MultipartFile file){
        RentingHouseEntity rentingHouse = rentingHouseRepository.findByHidAndRentingStatus(rentBody.getHid(),rentBody.getRentingStatus());
        RentingHouseEntity rentingHouseEntity = new RentingHouseEntity();
        if(rentingHouse != null && rentBody.getTid() == rentingHouse.getTid()){
//           Optional<TenantEntity> tenant = tenantService.findTenantById(rentingHouse.getTid());
//            Optional<HouseEntity> house =  houseService.findById(rentingHouse.getHid());
            ResponseEntity<UploadFileDTO> response = fileUpload.uploadRentPdf(file);
            rentingHouseEntity.setRid(rentingHouse.getRid());
            rentingHouseEntity.setHid(rentingHouse.getHid());
            rentingHouseEntity.setTid(rentingHouse.getTid());
            rentingHouseEntity.setRentingBook(rentingHouse.getRentingBook());
            rentingHouseEntity.setRentingImage(response.getBody().getImgPath());
            rentingHouseEntity.setRentingCheckIn(rentBody.getRentingCheckIn());
            rentingHouseEntity.setRentingStatus(1);
        }
        return rentingHouseRepository.save(rentingHouseEntity);
    }


    public RentDTO rentHouseByhidAndStatus(Integer hid,Integer status){
        RentingHouseEntity rentingHouse =  rentingHouseRepository.findByHidAndRentingStatus(hid,status);
        RentDTO rentDTO = new RentDTO();
        if(rentingHouse != null){
            Optional<TenantEntity> tenant = tenantService.findTenantById(rentingHouse.getTid());
            Optional<HouseEntity> house =  houseService.findById(rentingHouse.getHid());
            rentDTO.setTenant(tenant.get());
            rentDTO.setHouse(house.get());
            rentDTO.setRid(rentingHouse.getRid());
            rentDTO.setHid(rentingHouse.getHid());
            rentDTO.setTid(rentingHouse.getTid());
            rentDTO.setRentingBook(rentingHouse.getRentingBook());
            rentDTO.setRentingStatus(rentingHouse.getRentingStatus());
            rentDTO.setRentingImage(rentingHouse.getRentingImage());
            rentDTO.setRentingCheckIn(rentingHouse.getRentingCheckIn());
            rentDTO.setRentingCheckOut(rentingHouse.getRentingCheckOut());
            return rentDTO;
        }else return null;

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
