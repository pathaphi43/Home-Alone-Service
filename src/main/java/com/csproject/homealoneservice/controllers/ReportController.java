package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.PaymentSummaryDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.service.GenerateReportService;
import com.csproject.homealoneservice.service.HouseService;
import com.csproject.homealoneservice.service.RentingHouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    HouseService houseService;

    @Autowired
    RentingHouseService rentingHouseService;

    @Autowired
    GenerateReportService generateReportService;

    @GetMapping("")
    public String show() {return "Spring boot Report";};

    @PostMapping(value = "/payment-summary-pdf",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> paymentSummary(@RequestParam("mid")int mid, @RequestParam("dateFrom")String dateFrom, @RequestParam("dateTo")String dateTo){
        String payments =  generateReportService.exportPdf(mid,dateFrom,dateTo);
        if(payments != null){
            return new ResponseEntity<>(payments, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
