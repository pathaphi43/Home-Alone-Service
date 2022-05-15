package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.service.RentingHouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.SizeLimitExceededException;
import java.util.List;

@RestController
@RequestMapping(value = "rent")
public class RentingHouseController {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    RentingHouseService rentingHouseService;

    @GetMapping("")
    public String show() {return "Renting house service";};

    @PostMapping(value = "/houseAt",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDTO> queryRentHouseByhidAndStatus(@RequestBody RentingHouseEntity houseBody){
        return new  ResponseEntity<>(rentingHouseService.rentHouseByhidAndStatus(houseBody.getHid(),houseBody.getRentingStatus()), HttpStatus.OK);
    }

    @PostMapping(value = "/upload/rent-file")
    public ResponseEntity<RentingHouseEntity> confirmRentHouse(@RequestPart("rentData") @Validated RentingHouseEntity rentData, @RequestParam("file") MultipartFile file){
//        RentingHouseEntity rentingHouse= rentData;
        if (rentData != null){
          RentingHouseEntity rentingHouse=  rentingHouseService.confirmRentHouse(rentData,file);
          if(rentingHouse != null){
              return new ResponseEntity<>(rentingHouseService.confirmRentHouse(rentData,file), HttpStatus.OK);
          } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//   return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
