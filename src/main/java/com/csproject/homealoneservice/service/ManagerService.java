package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.TenantRepository;
import com.csproject.homealoneservice.entity.ManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    TenantRepository tenantRepository;


    public Optional<ManagerEntity> findManagerById(Integer id){

        if(managerRepository.findById(id).isPresent()){
            return managerRepository.findById(id);
        }else {
            return null;
        }
    }

    public List<ManagerEntity> findManagerAll(){
        return managerRepository.findAll();
    }


    public Optional<ManagerEntity> findManager(String username){
        return managerRepository.findByManagerUsername(username);
    }

    public ManagerEntity saveManager(ManagerEntity managerBody){
        ManagerEntity manager = new ManagerEntity();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!findManager(managerBody.getManagerUsername()).isPresent() && !tenantRepository.findByTenantUsername(managerBody.getManagerUsername()).isPresent()){
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
            managerRepository.save(manager);
            return manager;
        }else  return null;
    }

}
