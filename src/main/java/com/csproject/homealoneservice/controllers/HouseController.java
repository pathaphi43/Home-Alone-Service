package com.csproject.homealoneservice.controllers;



import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @GetMapping("/all")
    public Iterable<HouseEntity> findAllHouse() {
        return houseService.queryAllHouse();
    }

    @GetMapping("")
    public String show() {
        return "Spring boot service";
    };
}
