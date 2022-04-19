package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.TenantRepository;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    ManagerRepository managerRepository;

    public Optional<TenantEntity> findTenantById(Integer id){

        if(tenantRepository.findById(id).isPresent()){
            return tenantRepository.findById(id);
        }else {
            return null;
        }
    }

    public Optional<TenantEntity> findTenant(String username){
        return  tenantRepository.findByTenantUsername(username);
    }


    public TenantEntity saveManager(TenantEntity tenantBody){
        TenantEntity tenant = new TenantEntity();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(!findTenant(tenantBody.getTenantUsername()).isPresent() && !managerRepository.findByManagerUsername(tenantBody.getTenantUsername()).isPresent()){
            tenant.setTenantUsername(tenantBody.getTenantUsername());
            tenant.setTenantPassword(passwordEncoder.encode(tenantBody.getTenantPassword()));
            tenant.setTenantFirstname(tenantBody.getTenantFirstname());
            tenant.setTenantLastname(tenantBody.getTenantLastname());
            tenant.setTenantPhone(tenantBody.getTenantPhone());
            tenant.setTenantIdCard(passwordEncoder.encode(tenantBody.getTenantIdCard()));
            tenant.setTenantAddress(tenantBody.getTenantAddress());
            tenant.setTenantProvince(tenantBody.getTenantProvince());
            tenant.setTenantDistrict(tenantBody.getTenantDistrict());
            tenant.setTenantEmail(tenantBody.getTenantEmail());
            tenant.setTenantImage("http://homealone.comsciproject.com/img/local_avatar.png");
            tenant.setTenantStatus(1);
            tenantRepository.save(tenant);
            return tenant;
        }else  return null;
    }

}
