package com.csproject.homealoneservice.controllers;


import com.csproject.homealoneservice.dto.ConfirmRentDTO;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.dto.UploadMultipartFileDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.prepare.PrepareData;
import com.csproject.homealoneservice.service.HouseService;
import com.csproject.homealoneservice.service.RentingHouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/house")
public class HouseController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    HouseService houseService;

    @Autowired
    RentingHouseService rentingHouseService;

    @GetMapping("")
    public String show() {return "Spring boot service";};

    @GetMapping("test")
    public Map<String,String> showTest() {return houseService.test();};

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

    @GetMapping("/house-id/{hid}")
    public ResponseEntity<HouseEntity> findByHouseId(@PathVariable("hid") int hid){
       HouseEntity house= houseService.findById(hid).get();
        return new ResponseEntity(house, HttpStatus.OK);
    }

    @GetMapping("/house-name/{name}")
    public ResponseEntity<HouseEntity> findByHouseName(@PathVariable("name") String name){
        HouseEntity house = houseService.findByHouseName(name);
        if(house != null){
            return new ResponseEntity(house, HttpStatus.OK);
        }else  return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/HouseAndImage/{id}")
    public ResponseEntity<List<HouseDTO>> findHouseAndImages(@PathVariable("id") int id){
        return new ResponseEntity(houseService.queryHouseAndImage(id), HttpStatus.OK);
    }
    @PostMapping(value = "/save-image",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UploadMultipartFileDTO> saveImagesHouse(@RequestParam("hid") int hid, @RequestParam("files")  MultipartFile[] file){
        return new ResponseEntity(houseService.saveImage(hid,file),HttpStatus.OK);
    }

    @GetMapping("/house-image/{hid}")
    public ResponseEntity<List<HouseDTO>> findHouseAndImage(@PathVariable("hid") int hid){
        return new ResponseEntity(houseService.queryHouseAndImageByhid(hid), HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<List<HouseEntity>> findAllHouseByManagerId(@PathVariable("id") int id) {
        return new ResponseEntity(houseService.findByHouseId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/prerent",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HouseDTO> > queryAllHouseAndImageAndStatusIs(@RequestBody HouseEntity houseBody){
        return new  ResponseEntity<>(houseService.queryAllHouseAndImageAndStatusIs(houseBody.getMid(),houseBody.getHouseStatus()), HttpStatus.OK);
    }

    @PostMapping(value ="/rent", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentingHouseEntity>  rentHouse(@RequestBody RentDTO rentDTO) {
        return new  ResponseEntity<>(rentingHouseService.rentHouse(rentDTO.getHid(),rentDTO.getTid()), HttpStatus.OK);
    }

    @GetMapping("/cancelrent/{id}")
    public ResponseEntity<HouseEntity> cancelRentHouse(@PathVariable("id") int id) {
        return new ResponseEntity(rentingHouseService.cancelRentHouse(id), HttpStatus.OK);
    }

    @GetMapping("/cancel-house-after-rent/{id}")
    public ResponseEntity<HouseEntity> cancel(@PathVariable("id") int id) {
        return new ResponseEntity(rentingHouseService.cancelHouseAfterRent(id), HttpStatus.OK);
    }

    @PostMapping(value = "/insert",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HouseEntity> insertHouse(@RequestParam("houseData") String houseBody, @RequestParam("file") @Nullable MultipartFile file){
        try {
            ObjectMapper mapper = new ObjectMapper();
            HouseEntity modelDTO = mapper.readValue(houseBody, HouseEntity.class);
           if(modelDTO != null){
               return new  ResponseEntity<>(houseService.insertHouse(modelDTO,file), HttpStatus.OK);
           }else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/edit",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HouseEntity> editHouse(@RequestParam("houseData") String houseBody, @RequestParam("file") @Nullable MultipartFile file){
        try {
            ObjectMapper mapper = new ObjectMapper();
            HouseEntity modelDTO = mapper.readValue(houseBody, HouseEntity.class);
            if(modelDTO != null){
                return new  ResponseEntity<>(houseService.editHouse(modelDTO,file), HttpStatus.OK);
            }else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/dismiss-house/{hid}")
    public ResponseEntity<HouseEntity> dismissByid(@PathVariable("hid") int hid) {
        return new ResponseEntity<>(houseService.dismissHouseByHid(hid),HttpStatus.OK);
    }

    @DeleteMapping("/delete-image/{pid}")
    public ResponseEntity<?> deleteImageByPid(@PathVariable("pid") int pid) {
        try{
            houseService.deleteImageByPid(pid);
            return ResponseEntity.ok().build();
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-house/{hid}")
    public ResponseEntity<?> deleteByid(@PathVariable("hid") int hid) {
        try{
            houseService.deleteHouseByHid(hid);
            return ResponseEntity.noContent().build();
        }
        catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/search-status-like/{status}")
    public ResponseEntity<List<HouseEntity>> searchStatusLike(@PathVariable("status") int status) {
            return new ResponseEntity(houseService.findAllByHouseStatusIs(status), HttpStatus.OK);
    }
    @GetMapping("/search-status-not-like/{status}")
    public ResponseEntity<List<HouseEntity>> searchStatusNotLike(@PathVariable("status") int status) {
        return new ResponseEntity(houseService.findAllByHouseStatusIsNot(status), HttpStatus.OK);
    }

    @PostMapping(value = "/search-province-amphure-like",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<HouseEntity>> searchProvinceLikeOrAmphureLike(@RequestParam("province")@Nullable String province,@RequestParam("amphure")@Nullable String amphure){
        logger.info(province);
        logger.info(amphure);
       List<HouseEntity> house = houseService.findAllProvinceAndAmphureLike(province,amphure);
       if (!house.isEmpty()){
           return new ResponseEntity<>(house, HttpStatus.OK);
       }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}

    }

    @PostMapping(value = "/search-house",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<HouseEntity>> searchHouse(@RequestParam("houseName")@Nullable String houseName,@RequestParam("province")@Nullable String province,@RequestParam("amphure")@Nullable String amphure,@RequestParam("status")@Nullable int status){
        List<HouseEntity> house;
        if(status == 0){
            house = houseService.findAllByhouseNameProvinceAmphureStatus(houseName,province,amphure, PrepareData.getSearchStatusList1());
       }else if(status == 1){
           house = houseService.findAllByhouseNameProvinceAmphureStatus(houseName,province,amphure, PrepareData.getSearchStatusList2());
       }else {
            house = houseService.findAllByhouseNameProvinceAmphureStatus(houseName,province,amphure, PrepareData.getSearchStatusList3());
       }
        if (!house.isEmpty()){
            return new ResponseEntity<>(house, HttpStatus.OK);
        }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}

    }

    @PostMapping(value = "/search-house-provice",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<HouseEntity>> searchHouse(@RequestParam("houseName")@Nullable String houseName,@RequestParam("province")@Nullable String province,@RequestParam("status")@Nullable int status){
        List<HouseEntity> house;
        if(status == 0){
            house = houseService.findAllByhouseNameProvinceStatus(houseName,province, PrepareData.getSearchStatusList1());
        }else if(status == 1){
            house = houseService.findAllByhouseNameProvinceStatus(houseName,province,PrepareData.getSearchStatusList2());
        }else {
            house = houseService.findAllByhouseNameProvinceStatus(houseName,province, PrepareData.getSearchStatusList3());
        }
        if (!house.isEmpty()){
            return new ResponseEntity<>(house, HttpStatus.OK);
        }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}

    }

    @PostMapping(value = "/search-house-status",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<HouseEntity>> searchHouse(@RequestParam("houseName")@Nullable String houseName,@RequestParam("status")@Nullable String status){
        List<HouseEntity> house= null;
        List<Integer> StatusList = null;
        if(status.equals("0")){
            System.out.println(houseName);
            System.out.println(PrepareData.getSearchStatusList1());
            house = houseService.findAllByhouseNameAndStatusIn(houseName,PrepareData.getSearchStatusList1());
            if (!house.isEmpty()){
                return new ResponseEntity<>(house, HttpStatus.OK);
            }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
        }else if(status.equals("1")){
            StatusList = PrepareData.getSearchStatusList2();
            house = houseService.findAllByhouseNameAndStatusIn(houseName,StatusList);
            if (!house.isEmpty()){
                return new ResponseEntity<>(house, HttpStatus.OK);
            }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
        }else {
            StatusList = PrepareData.getSearchStatusList3();
            house = houseService.findAllByhouseNameAndStatusIn(houseName,StatusList);
            if (!house.isEmpty()){
                return new ResponseEntity<>(house, HttpStatus.OK);
            }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
        }


    }

    @PostMapping(value = "/search-province-amphure-is",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<List<HouseEntity>> searchProvinceIsOrAmphureIs(@RequestParam("province")@Nullable String province,@RequestParam("amphure")@Nullable String amphure){
        logger.info(province);
        logger.info(amphure);
        List<HouseEntity> house = houseService.findAllProvinceAndAmphure(province,amphure);
        if (!house.isEmpty()){
            return new ResponseEntity<>(house, HttpStatus.OK);
        }else {return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}

    }


}
