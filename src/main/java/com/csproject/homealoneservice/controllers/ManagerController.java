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

    @PostMapping(value = "/signup",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ManagerEntity> signUp(@RequestBody ManagerEntity managerBody){
        ManagerEntity manager = new ManagerEntity();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!managerService.findManager(managerBody.getManagerUsername()).isPresent()){
        manager.setManagerUsername(managerBody.getManagerUsername());
        manager.setManagerPassword(passwordEncoder.encode(managerBody.getManagerPassword()));
        manager.setManagerFirstname(managerBody.getManagerFirstname());
        manager.setManagerLastname(managerBody.getManagerLastname());
        manager.setManagerOffice(managerBody.getManagerOffice());
        manager.setManagerFacebook(managerBody.getManagerFacebook());
        manager.setManagerLineid(managerBody.getManagerLineid());
        manager.setManagerPhone(managerBody.getManagerPhone());
        manager.setManagerImage("http://homealone.comsciproject.com/img/local_avatar.png");
        manager.setManagerStatus(0);
        managerService.saveManager(manager);
            return new ResponseEntity<>(manager, HttpStatus.OK);
        }else  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
