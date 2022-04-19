package com.csproject.homealoneservice.controllers;


import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @GetMapping("")
    public String show() {return "Spring boot service";};

    @GetMapping("/all")
    public Iterable<HouseEntity> findAllHouse() {
        return houseService.queryAllHouse();
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<HouseEntity>> findAllHouseByManagerId(@PathVariable("id") int id) {
        return new ResponseEntity(houseService.findByHouseId(id), HttpStatus.OK);
    }


    @GetMapping("/lazy/{id}")
    public List<HouseEntity> findAllHouseLazy(@PathVariable("id") int id) {
        return houseService.findOrderedByHidLimitedTo(id);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<HouseEntity>> findBynames(@PathVariable("name") String name) {
            if(!houseService.findAllHouseByname(name).isEmpty()){
                return new ResponseEntity(houseService.findAllHouseByname(name), HttpStatus.OK);
            }else return new ResponseEntity(name+" Not found!!! ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
