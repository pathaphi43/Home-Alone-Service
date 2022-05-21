package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.ConfirmRentDTO;
import com.csproject.homealoneservice.dto.PaymentDTO;
import com.csproject.homealoneservice.dto.PaymentSearchDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "")
    public String test(){
        return "PaymentContoller";
    }

    @GetMapping(value = "/all")
    public List<PaymentEntity> findManagerAll(){
        return paymentService.findAllPayment();
    }

    @PostMapping(value = "/save-payment")
    public ResponseEntity<PaymentEntity>  savePayment(@RequestBody PaymentEntity paymentBody){

        return new ResponseEntity<>(paymentService.savePayment(paymentBody), HttpStatus.OK);
    }

    @GetMapping("/house-rent/{mid}")
    public ResponseEntity<List<HouseEntity>> findAllHouseByManagerId(@PathVariable("mid") int mid) {
        return new ResponseEntity(paymentService.findAllPaymentByHouseManagerId(mid), HttpStatus.OK);
    }

    @GetMapping("/house-rent-tenant/{tid}")
    public ResponseEntity<List<HouseEntity>> findAllHouseByTenantId(@PathVariable("tid") int tid) {
        return new ResponseEntity(paymentService.findAllPaymentByHouseTenantId(tid), HttpStatus.OK);
    }

    @PostMapping(value = "/date-between")
    public ResponseEntity<List<PaymentDTO>> findAllPaymentByHouseManagerIdBet(@RequestBody PaymentSearchDTO paymentSearchDTO) {
        return new ResponseEntity(paymentService.findAllPaymentByHouseManagerIdInMonth(paymentSearchDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/tenant-rent",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> paymentTenantRent(@RequestParam("id")int id,@RequestParam("date")String date, @RequestParam("file") MultipartFile file){
        logger.info(file.getOriginalFilename());
            if (!file.isEmpty()){
                PaymentEntity rentingHouse=  paymentService.tenantPaymentRent(id,date,file);
                if(rentingHouse != null){
                    return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
                } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/tenant-water",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> paymentTenantWater(@RequestParam("id")int id,@RequestParam("date")String date, @RequestParam("file") MultipartFile file){
        logger.info(file.getOriginalFilename());
        if (!file.isEmpty()){
            PaymentEntity rentingHouse=  paymentService.tenantPaymentWater(id,date,file);
            if(rentingHouse != null){
                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/tenant-Electric",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> paymentTenantElectric(@RequestParam("id")int id,@RequestParam("date")String date, @RequestParam("file") MultipartFile file){
        logger.info(file.getOriginalFilename());
        if (!file.isEmpty()){
            PaymentEntity rentingHouse=  paymentService.tenantPaymentElectric(id,date,file);
            if(rentingHouse != null){
                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
