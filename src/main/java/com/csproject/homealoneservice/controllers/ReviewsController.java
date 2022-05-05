package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.ReviewsDTO;
import com.csproject.homealoneservice.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
