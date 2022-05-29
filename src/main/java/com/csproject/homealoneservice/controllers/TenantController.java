package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
import com.csproject.homealoneservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tenant")
public class TenantController {

    @Autowired
    TenantService tenantService;

    @GetMapping("")
    public String show() {return "TenantContoller";};

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ManagerEntity> findManagerId(@PathVariable("id") Integer id){
        return new ResponseEntity(tenantService.findTenantById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<TenantEntity> signUp(@RequestBody TenantEntity tenantBody){
        TenantEntity result = tenantService.saveTenant(tenantBody);
        if( result != null){
            return new ResponseEntity(result, HttpStatus.OK);
        }else return new ResponseEntity("สมัครสมาชิกไม่สำเร็จ ชื่อผู้ใช้ '"+ tenantBody.getTenantUsername() + "' อาจซ้ำกัน!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(value = "check/id-card/{IDCard}")
    public ResponseEntity<TenantEntity> checkIDCard(@PathVariable("IDCard") String IDCard){
        return new ResponseEntity(tenantService.checkIDCard(IDCard),HttpStatus.OK);
    }

    @GetMapping(value = "check/phone/{phone}")
    public ResponseEntity<TenantEntity> checkPhoneNumber(@PathVariable("phone") String phone){
        return new ResponseEntity(tenantService.checkPhoneNumber(phone),HttpStatus.OK);
    }

    @GetMapping(value = "check/email/{email}")
    public ResponseEntity<TenantEntity> checkEmail(@PathVariable("email") String email){
        return new ResponseEntity(tenantService.checkEmail(email),HttpStatus.OK);
    }
}
