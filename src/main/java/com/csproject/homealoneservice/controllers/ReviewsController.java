package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.PreReviewDTO;
import com.csproject.homealoneservice.dto.ReviewsDTO;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.csproject.homealoneservice.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/review")
public class ReviewsController {

    @Autowired
    ReviewsService reviewsService;

    @GetMapping("")
    public String show(){
        return "Reviews Controller";
    }

    @GetMapping("/AndImage/{id}")
    public ResponseEntity<List<ReviewsDTO>> findReviewsByHouseId(@PathVariable("id") Integer id){
        return new ResponseEntity<>(reviewsService.queryReviewByHid(id), HttpStatus.OK);
    }

    @PostMapping(value = "/pre-review-info",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<PreReviewDTO>> editPaymentTenantWater(@RequestParam("tid")int tid,@RequestParam("status")int status){
        return new ResponseEntity<>(reviewsService.queryHousePreReviewByTid(tid,status),HttpStatus.OK);
    }
}
