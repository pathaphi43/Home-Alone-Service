package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.TenantRepository;
import com.csproject.homealoneservice.entity.TenantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    public Optional<TenantEntity> findTenant(String username){
        return  tenantRepository.findByTenantUsername(username);
    }

}
