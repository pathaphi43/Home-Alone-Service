package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.csproject.homealoneservice.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
