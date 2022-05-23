package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.RentingHouseRepository;
import com.csproject.homealoneservice.dto.ConfirmRentDTO;
import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
import com.csproject.homealoneservice.enumeration.HouseEnum;
import com.csproject.homealoneservice.enumeration.StatusEnum;
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

    public RentingHouseEntity confirmRentHouse(ConfirmRentDTO rentBody, MultipartFile file){
        RentingHouseEntity rentingHouse = rentingHouseRepository.findByHidAndRentingStatus(rentBody.getHid(),0);
        RentingHouseEntity rentingHouseEntity = new RentingHouseEntity();
        if(rentingHouse != null && rentBody.getTid() == rentingHouse.getTid()){
//           Optional<TenantEntity> tenant = tenantService.findTenantById(rentingHouse.getTid());
//            Optional<HouseEntity> house =  houseService.findById(rentingHouse.getHid());
            ResponseEntity<UploadFileDTO> response = null ;
            if(!file.isEmpty()){
                response = fileUpload.uploadRentPdf(file,rentBody);
            }
            rentingHouseEntity.setRid(rentingHouse.getRid());
            rentingHouseEntity.setHid(rentingHouse.getHid());
            rentingHouseEntity.setTid(rentingHouse.getTid());
            rentingHouseEntity.setRentingBook(rentingHouse.getRentingBook());
            rentingHouseEntity.setRentingImage(file.isEmpty() ? null :response.getBody().getImgPath());
            rentingHouseEntity.setRentingCheckIn(Timestamp.valueOf(rentBody.getRentingCheckIn()));
            rentingHouseEntity.setRentingStatus(1);
             RentingHouseEntity result = rentingHouseRepository.save(rentingHouseEntity);

            if(result != null){
                HouseEntity house = houseService.findById(rentBody.getHid()).get();
                house.setHid(rentBody.getHid());
                house.setHouseStatus(1);
                houseService.houseRepository.save(house);
                return  result;
            }else return null;


        }

        return null;
    }

    public RentDTO rentHouseByhidAndStatus(Integer hid,Integer status){
        RentingHouseEntity rentingHouse =  rentingHouseRepository.findByHidAndRentingStatus(hid,status);
        RentDTO rentDTO = new RentDTO();
        if(rentingHouse != null){
            Optional<TenantEntity> tenant = tenantService.findTenantById(rentingHouse.getTid());
            Optional<HouseEntity> house =  houseService.findById(rentingHouse.getHid());
//            rentDTO.setTenant(tenant.get());
//            rentDTO.setHouse(house.get());
            rentDTO.setHouseName(house.get().getHouseName());
            rentDTO.setHouseRent(house.get().getHouseRent());
            rentDTO.setHouseDeposit(house.get().getHouseDeposit());
            rentDTO.setHouseInsurance(house.get().getHouseInsurance());
            rentDTO.setHouseAddress(house.get().getHouseAddress());
            rentDTO.setHouseProvince(house.get().getHouseProvince());
            rentDTO.setHouseDistrict(house.get().getHouseDistrict());
            rentDTO.setHouseElectric(house.get().getHouseElectric());
            rentDTO.setHouseWater(house.get().getHouseWater());
            rentDTO.setTenantFirstname(tenant.get().getTenantFirstname());
            rentDTO.setTenantLastname(tenant.get().getTenantLastname());
            rentDTO.setTenantPhone(tenant.get().getTenantPhone());
            rentDTO.setTenantUsername(tenant.get().getTenantUsername());
            rentDTO.setTenantImage(tenant.get().getTenantImage());
            rentDTO.setTenantProvince(tenant.get().getTenantProvince());
            rentDTO.setTenantDistrict(tenant.get().getTenantDistrict());
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

    public HouseEntity cancelRentHouse(Integer id){
        RentingHouseEntity rentingHouse = rentingHouseRepository.findByHidAndRentingStatus(id,StatusEnum.Prepare_Status.getStatus());
        rentingHouse.setRid(rentingHouse.getRid());
        rentingHouse.setRentingStatus(StatusEnum.Cancel_Status.getStatus());
        HouseEntity house = houseService.findById(id).get();
        house.setHid(id);
        house.setHouseStatus(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        rentingHouseRepository.save(rentingHouse);
        return houseService.houseRepository.save(house);
    }

    public HouseEntity cancelHouseAfterRent(Integer id){
        RentingHouseEntity rentingHouse = rentingHouseRepository.findByHidAndRentingStatus(id,StatusEnum.Normal_Status.getStatus());
        rentingHouse.setRid(rentingHouse.getRid());
        rentingHouse.setRentingStatus(StatusEnum.Cancel_Status.getStatus());
        rentingHouse.setRentingCheckOut(Timestamp.valueOf(zdt.toLocalDateTime()));
        HouseEntity house = houseService.findById(id).get();
        house.setHid(id);
        house.setHouseStatus(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        rentingHouseRepository.save(rentingHouse);
        return houseService.houseRepository.save(house);
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
