package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.ConfirmRentDTO;
import com.csproject.homealoneservice.dto.PreReviewDTO;
import com.csproject.homealoneservice.dto.ReviewsDTO;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.entity.ReviewsEntity;
import com.csproject.homealoneservice.entity.ReviewsImageEntity;
import com.csproject.homealoneservice.service.ReviewsService;
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
@RequestMapping(value = "/review")
public class ReviewsController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

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

    @GetMapping("/review-tid/{tid}")
    public ResponseEntity<List<ReviewsEntity>> findReviewsByHouseTid(@PathVariable("tid") Integer tid){
        return new ResponseEntity<>(reviewsService.queryReviewByTid(tid), HttpStatus.OK);
    }


    @PostMapping(value = "/pre-review-info",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<PreReviewDTO>> editPaymentTenantWater(@RequestParam("tid")int tid,@RequestParam("status")int status){
        return new ResponseEntity<>(reviewsService.queryHousePreReviewByTid(tid,status),HttpStatus.OK);
    }

    @PostMapping(value = "/save-review",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ReviewsEntity> saveReview(@RequestParam("reviewBody")String reviewBody,@Nullable MultipartFile[]  file){
        try {
            ObjectMapper mapper = new ObjectMapper();
            ReviewsEntity modelDTO = mapper.readValue(reviewBody, ReviewsEntity.class);
            if (modelDTO != null){
                ReviewsEntity reviewsEntity=  reviewsService.saveReview(modelDTO,file);
                if(reviewsEntity != null){
                    return new ResponseEntity<>(reviewsEntity,HttpStatus.OK);
                } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
