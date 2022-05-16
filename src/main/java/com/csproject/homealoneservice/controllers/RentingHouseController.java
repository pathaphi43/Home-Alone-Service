package com.csproject.homealoneservice.controllers;


import com.csproject.homealoneservice.dto.RentDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.service.RentingHouseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SerializationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;


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

//    @PostMapping(value = "/upload/rent-file",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<String> confirmRentHouse(@RequestPart("rentData") String  rentData, @RequestPart("rentingCheckIn") String rentingCheckIn,
//                                                   @RequestPart("rentingCheckOut") String rentingCheckOut, @RequestParam("file") MultipartFile file){
//
//
//
//
//       return new ResponseEntity<>("JWT", HttpStatus.OK);
////        RentDTO rentDTO= new RentDTO(hid,tid,rid,rentingCheckIn,null);
////        logger.info(rentDTO);
////        if (rentDTO != null){
////          RentingHouseEntity rentingHouse=  rentingHouseService.confirmRentHouse(rentDTO,file);
////          if(rentingHouse != null){
////              return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
////          } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
////   return new ResponseEntity<>(null, HttpStatus.OK);
//    }

    @PostMapping(value = "/upload/rent-file",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE} ,produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> confirmRentHouseBody(@ModelAttribute("rentData")@Validated RentDTO rentDTO, @RequestParam("file") MultipartFile file){
        logger.info(rentDTO.getRid());
        logger.info(file.getOriginalFilename());
//        if (!rentDTO.toString().isEmpty()){
//            RentingHouseEntity rentingHouse=  rentingHouseService.confirmRentHouse(rentDTO,file);
//            if(rentingHouse != null){
//                return new ResponseEntity<>(rentingHouse, HttpStatus.OK);
//            } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
   return new ResponseEntity<>("OK", HttpStatus.OK);
    }
public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
    ByteArrayInputStream in = new ByteArrayInputStream(data);
    ObjectInputStream is = new ObjectInputStream(in);
    return is.readObject();
}
}
