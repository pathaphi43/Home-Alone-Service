package com.csproject.homealoneservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class Controller {
    @GetMapping(value = "")
    public String test(){
        return "Home Alone Web Service";
    }
}
