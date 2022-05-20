package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.*;
import com.csproject.homealoneservice.dto.PaymentDTO;
import com.csproject.homealoneservice.dto.PaymentSearchDTO;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.*;
import com.csproject.homealoneservice.enumeration.PayStatusEnum;
import com.csproject.homealoneservice.enumeration.StatusEnum;
import com.csproject.homealoneservice.prepare.PrepareData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    RentingHouseRepository rentingHouseRepository;

    @Autowired
    FileUpload fileUpload;

    List<Integer> rentingStatusList = PrepareData.getRentingStatusList();

    public List<PaymentEntity> findAllPayment() {
        return paymentRepository.findAll();
    }

    public PaymentEntity savePayment(PaymentEntity paymentBody) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setRid(paymentBody.getRid());
        paymentEntity.setInstallment(paymentBody.getInstallment());
        paymentEntity.setPayHouseAmount(paymentBody.getPayHouseAmount());
        paymentEntity.setPayHouseEnd(paymentBody.getPayHouseEnd());
        paymentEntity.setPayHouseEnd(paymentBody.getPayHouseEnd());
        paymentEntity.setPayHouseStatus(StatusEnum.Prepare_Status.getStatus());
        paymentEntity.setPayElecAmount(paymentBody.getPayElecAmount());
        paymentEntity.setPayElecInmonth(paymentBody.getPayElecInmonth());
        paymentEntity.setPayElecEnd(paymentBody.getPayElecEnd());
        paymentEntity.setPayElecStatus(StatusEnum.Prepare_Status.getStatus());
        paymentEntity.setPayWaterAmount(paymentBody.getPayWaterAmount());
        paymentEntity.setPayWaterInmonth(paymentBody.getPayWaterInmonth());
        paymentEntity.setPayWaterEnd(paymentBody.getPayWaterEnd());
        paymentEntity.setPayWaterStatus(StatusEnum.Prepare_Status.getStatus());
        return paymentRepository.save(paymentEntity);
    }

    public List<PaymentDTO> findAllPaymentByHouseManagerId(Integer mid) {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        ManagerEntity manager = managerRepository.findById(mid).get();
        List<HouseEntity> houseEntityList = houseRepository.findByMid(mid);
        for (HouseEntity house : houseEntityList) {
            List<RentingHouseEntity> rentingHouses = rentingHouseRepository.findAllByHidAndRentingStatusIn(house.getHid(), rentingStatusList);
            for (RentingHouseEntity renting : rentingHouses) {
                List<PaymentEntity> payments = paymentRepository.findAllByRid(renting.getRid());
                logger.info(!payments.isEmpty());
                if (!payments.isEmpty()) {
                    TenantEntity tenant = tenantRepository.findById(renting.getTid()).get();
                    paymentDTOS.add(new PaymentDTO(house, tenant, payments));
                }
            }
        }
        return paymentDTOS;
    }

    public List<PaymentDTO> findAllPaymentByHouseTenantId(Integer tid) {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
//        ManagerEntity manager = managerRepository.findById(mid).get();
        List<RentingHouseEntity> rentingHouses = rentingHouseRepository.findAllByTidAndRentingStatusIn(tid, rentingStatusList);
        TenantEntity tenant = tenantRepository.findById(tid).get();
        for (RentingHouseEntity rent : rentingHouses) {
            HouseEntity house = houseRepository.findById(rent.getHid()).get();
            List<PaymentEntity> payments = paymentRepository.findAllByRid(rent.getRid());
            if (!payments.isEmpty()) {
                paymentDTOS.add(new PaymentDTO(house, tenant, payments));
            }
        }

        return paymentDTOS;
    }

    public PaymentEntity tenantPaymentRent(int id,String date, MultipartFile file){
       PaymentEntity payment =  paymentRepository.findById(id).get();
        payment.setPayHouseStatus(PayStatusEnum.Prepare_Status.getStatus());
        payment.setPayHouseDate(Timestamp.valueOf(date));
        ResponseEntity<UploadFileDTO> response = null ;
        if(!file.isEmpty()){
            response = fileUpload.uploadRent(file,id);
        }
        payment.setPayHouseImg(response.getBody().getImgPath());
        return paymentRepository.save(payment);
    }

    public PaymentEntity tenantPaymentWater(PaymentEntity paymentBody, MultipartFile file){
        PaymentEntity payment =  paymentRepository.findById(paymentBody.getId()).get();

        return paymentBody;
    }
    public PaymentEntity tenantPaymentElectric(PaymentEntity paymentBody, MultipartFile file){
        PaymentEntity payment =  paymentRepository.findById(paymentBody.getId()).get();

        return paymentBody;
    }

    public List<PaymentDTO> findAllPaymentByHouseManagerIdInMonth(PaymentSearchDTO paymentBody) {
        Timestamp start = Timestamp.valueOf(paymentBody.getTimeStart());
        Timestamp end = Timestamp.valueOf(paymentBody.getTimeEnd());
        List<PaymentEntity> payments = paymentRepository.findAll(PaymentSpecification.dateBetween(start, end));
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (PaymentEntity payment : payments) {
            logger.info(payment);
            RentingHouseEntity rentingHouse = rentingHouseRepository.findById(payment.getRid()).get();
            TenantEntity tenant = tenantRepository.findById(rentingHouse.getTid()).get();
            HouseEntity house = houseRepository.findById(rentingHouse.getHid()).get();
            paymentDTOS.add(new PaymentDTO(house, tenant, payment));
        }
        return paymentDTOS;
    }
}
