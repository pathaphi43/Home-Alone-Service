package com.csproject.homealoneservice.controllers;

import com.csproject.homealoneservice.configurations.Configuration;
import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.service.ManagerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    ManagerService managerService;

    @Autowired
    Configuration configuration;


    @GetMapping(value = "")
    public String test(){
        return "ManagerContoller";
    }

    @GetMapping(value = "all")
    public List<ManagerEntity> findManagerAll(){
        return managerService.findManagerAll();
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<ManagerEntity> findManagerId(@PathVariable("id") Integer id){
        return new ResponseEntity(managerService.findManagerById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ManagerEntity> signUp(@RequestBody ManagerEntity managerBody){
        ManagerEntity result = managerService.saveManager(managerBody);
       if( result != null){
           return new ResponseEntity(result,HttpStatus.OK);
       }else return new ResponseEntity("สมัครสมาชิกไม่สำเร็จ ชื่อผู้ใช้ '"+ managerBody.getManagerUsername() + "' อาจซ้ำกัน!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
