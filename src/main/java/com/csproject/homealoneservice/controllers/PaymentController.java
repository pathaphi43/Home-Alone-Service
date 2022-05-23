package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.ConfirmRentDTO;
import com.csproject.homealoneservice.dto.PaymentDTO;
import com.csproject.homealoneservice.dto.PaymentSearchDTO;
import com.csproject.homealoneservice.dto.PaymentSummaryDTO;
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

    @PostMapping(value = "/tenant-electric",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> paymentTenantElectric(@RequestParam("id")int id,@RequestParam("date")String date, @RequestParam("file") MultipartFile file){
        logger.info(file.getOriginalFilename());
        if (!file.isEmpty()){
            PaymentEntity rentingHouse=  paymentService.tenantPaymentElectric(id,date,file);
            if(rentingHouse != null){
                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/tenant-rent/{id}")
    public ResponseEntity<PaymentEntity> confirmPaymentTenantRent(@PathVariable("id") int id) {
        return new ResponseEntity(paymentService.tenantConfirmPaymentRent(id), HttpStatus.OK);
    }
    @GetMapping("/tenant-water/{id}")
    public ResponseEntity<PaymentEntity> confirmPaymentTenantWater(@PathVariable("id") int id) {
        return new ResponseEntity(paymentService.tenantConfirmPaymentWater(id), HttpStatus.OK);
    }
    @GetMapping("/tenant-electric/{id}")
    public ResponseEntity<PaymentEntity> confirmPaymentTenantElectric(@PathVariable("id") int id) {
        return new ResponseEntity(paymentService.tenantConfirmPaymentElectric(id), HttpStatus.OK);
    }

    @PostMapping(value = "/edit-tenant-rent",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> editPaymentTenantRent(@RequestBody PaymentEntity payment){
        if (payment != null){
            PaymentEntity rentingHouse=  paymentService.editRentPayment(payment);
            if(rentingHouse != null){
                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/edit-tenant-water",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> editPaymentTenantWater(@RequestBody PaymentEntity payment){
        if (payment != null){
            PaymentEntity rentingHouse=  paymentService.editWaterPayment(payment);
            if(rentingHouse != null){
                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/edit-tenant-electric",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PaymentEntity> editPaymentTenantElectric(@RequestBody PaymentEntity payment){
        if (payment != null){
            PaymentEntity rentingHouse=  paymentService.editElecPayment(payment);
            if(rentingHouse != null){
                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/payment-summary",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<PaymentSummaryDTO>> paymentSummary(@RequestParam("mid")int mid,@RequestParam("dateFrom")String dateFrom,@RequestParam("dateTo")String dateTo){
        List<PaymentSummaryDTO> payments=  paymentService.paymentSummary(mid,dateFrom,dateTo);
            if(payments != null){
                return new ResponseEntity<>(payments, HttpStatus.OK);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
