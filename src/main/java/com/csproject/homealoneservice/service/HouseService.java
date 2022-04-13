package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.HouseSpecification;
import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;


    public Iterable<HouseEntity> queryAllHouse() {
        return houseRepository.findAll();
    }

    public List<HouseEntity> findAllHouseByname(String name) {
        return houseRepository.findAll(HouseSpecification.likeHouseName(name));
    }
}
