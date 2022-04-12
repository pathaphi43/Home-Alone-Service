package com.csproject.homealoneservice.controllers;



import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @GetMapping("/all")
    public Iterable<HouseEntity> findAllHouse() {
        return houseService.queryAllHouse();
    }

    @GetMapping("")
    public String show(){
      return "Spring boot service";
    };

    @GetMapping("/search/{name}")
    public Iterable<HouseEntity> findBynames(@PathVariable("name") String name) {
        return houseService.findByname(name);
    }
}
