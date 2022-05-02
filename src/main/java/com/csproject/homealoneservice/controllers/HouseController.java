package com.csproject.homealoneservice.controllers;


import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.service.HouseService;
import com.csproject.homealoneservice.service.RentingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @Autowired
    RentingHouseService rentingHouseService;

    @GetMapping("")
    public String show() {return "Spring boot service";};

    @GetMapping("/all")
    public Iterable<HouseEntity> findAllHouse() {
        return houseService.queryAllHouse();
    }

    @GetMapping("/AllAndImage")
    public ResponseEntity<List<HouseDTO>> findHouseAndImage(){
        return new ResponseEntity(houseService.queryAllHouseAndImage(), HttpStatus.OK);
    }

    @GetMapping("/AllAndImageAndStatus")
    public ResponseEntity<List<HouseDTO>> findHouseAndImageAndStatus(){
        return new ResponseEntity(houseService.queryAllHouseAndImageAndStatus(), HttpStatus.OK);
    }

    @GetMapping("/HouseAndImage/{id}")
    public ResponseEntity<List<HouseDTO>> findHouseAndImages(@PathVariable("id") int id){
        return new ResponseEntity(houseService.queryHouseAndImage(id), HttpStatus.OK);
    }

    @GetMapping("/rent/{id}")
    public ResponseEntity<HouseEntity> rentHouse(@PathVariable("id") int id) {
        return new ResponseEntity(houseService.rentHouse(id), HttpStatus.OK);
    }

    @GetMapping("/cancelrent/{id}")
    public ResponseEntity<HouseEntity> cancelRentHouse(@PathVariable("id") int id) {
        return new ResponseEntity(houseService.cancelRentHouse(id), HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<HouseEntity>> findAllHouseByManagerId(@PathVariable("id") int id) {
        return new ResponseEntity(houseService.findByHouseId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/prerent",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentingHouseEntity> queryAllHouseAndImageAndStatusIs(@RequestBody RentDTO rentDTO){
        return new  ResponseEntity<>(rentingHouseService.rentHouse(rentDTO.getHid(),rentDTO.getTid()), HttpStatus.OK);
    }

    @PostMapping(value = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HouseEntity> insertHouse(@RequestBody HouseEntity houseBody){
        return new  ResponseEntity<>(houseService.insertHouse(houseBody), HttpStatus.OK);
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
