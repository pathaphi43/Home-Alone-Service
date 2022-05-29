package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.TenantRepository;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    FileUpload fileUpload;

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

    public Optional<ManagerEntity> findManager(String username) {
        return managerRepository.findByManagerUsername(username);
    }

    public String saveTenantProfile(String username, MultipartFile file) {
        ResponseEntity<UploadFileDTO> response = fileUpload.uploadProfile(file);
        if (response.getStatusCode() == HttpStatus.OK) {
            TenantEntity tenant = new TenantEntity();
            Optional<TenantEntity> tenants = tenantRepository.findByTenantUsername(username);
            if (tenants.isPresent()) {
                tenant.setTenantUsername(tenants.get().getTenantUsername());
                tenant.setTenantPassword(tenants.get().getTenantPassword());
                tenant.setTenantFirstname(tenants.get().getTenantFirstname());
                tenant.setTenantLastname(tenants.get().getTenantLastname());
                tenant.setTenantPhone(tenants.get().getTenantPhone());
                tenant.setTenantIdCard(tenants.get().getTenantIdCard());
                tenant.setTenantAddress(tenants.get().getTenantAddress());
                tenant.setTenantProvince(tenants.get().getTenantProvince());
                tenant.setTenantDistrict(tenants.get().getTenantDistrict());
                tenant.setTenantEmail(tenants.get().getTenantEmail());
                tenant.setTenantImage(response.getBody().getImgPath());
                tenant.setTenantStatus(tenants.get().getTenantStatus());
                tenant.setTid(tenants.get().getTid());
                TenantEntity result = tenantRepository.save(tenant);
                return result.getTenantImage();
            }else return null;
        }else return null;

    }

    public TenantEntity saveTenant(TenantEntity tenantBody){
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


    public TenantEntity  checkIDCard(String tenantIDCard){
        List<TenantEntity> tenantEntities = tenantRepository.findAll();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        TenantEntity tenantCheck = new TenantEntity();
        if(!tenantIDCard.isEmpty()){
            for (TenantEntity tenant:tenantEntities){
                if(passwordEncoder.matches(tenantIDCard,tenant.getTenantIdCard())){
                     tenantCheck = tenant;
                     break;
                }
            }
        }
        return tenantCheck;
    }

    public TenantEntity  checkPhoneNumber(String phone){
        List<TenantEntity> tenantEntities = tenantRepository.findAll();
        TenantEntity tenantCheck = new TenantEntity();
        if(!phone.isEmpty()){
            for (TenantEntity tenant:tenantEntities){
                if(tenant.getTenantPhone().equals(phone)){
                    tenantCheck = tenant;
                    break;
                }
            }
        }
        return tenantCheck;
    }

    public TenantEntity  checkEmail(String email){
        List<TenantEntity> tenantEntities = tenantRepository.findAll();
        TenantEntity tenantCheck = new TenantEntity();
        if(!email.isEmpty()){
            for (TenantEntity tenant:tenantEntities){
                if(tenant.getTenantEmail().equals(email)){
                    tenantCheck = tenant;
                    break;
                }
            }
        }
        return tenantCheck;
    }

}
