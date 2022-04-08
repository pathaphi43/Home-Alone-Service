package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    public String showRegistrationForm(WebRequest request, Model model) {

//        ManagerDTO managerDTO = new ManagerDTO();
//        model.addAllAttributes("Manager",ManagerDTO());
        return "registration";

    }

    public Optional<ManagerEntity> findManager(String username){
        return managerRepository.findByManagerUsername(username);
    }

    public ManagerEntity saveManager(ManagerEntity manager){
        return managerRepository.save(manager);
    }

}
