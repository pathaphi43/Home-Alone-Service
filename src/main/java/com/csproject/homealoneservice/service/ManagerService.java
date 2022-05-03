package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.TenantRepository;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.enumeration.ManagerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    FileUpload fileUpload;


    public Optional<ManagerEntity> findManagerById(Integer id) {

        if (managerRepository.findById(id).isPresent()) {
            return managerRepository.findById(id);
        } else {
            return null;
        }
    }

    public List<ManagerEntity> findManagerAll() {
        return managerRepository.findAll();
    }


    public Optional<ManagerEntity> findManager(String username) {
        return managerRepository.findByManagerUsername(username);
    }

//    public ResponseEntity<UploadFileDTO> uploadProfile(MultipartFile file) {
//        String url = "http://homealone.comsciproject.com/manager/upload/profile";
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
//        try {
//            File convFile = new File(file.getOriginalFilename());
//            FileOutputStream fos = new FileOutputStream(convFile);
//            fos.write(file.getBytes());
//            fos.close();
//            //***Converd File
//            params.add("image_file", new FileSystemResource(convFile));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
//        Map<String, Object> FeedBackStatus=new HashMap<String, Object>();
//        ResponseEntity<UploadFileDTO> response = restTemplate.exchange(url, HttpMethod.POST, request, UploadFileDTO.class);
//        return response;
//    }


    public String saveManagerProfile(String username, MultipartFile file) {
        ResponseEntity<UploadFileDTO> response = fileUpload.uploadProfile(file);
        if (response.getStatusCode() == HttpStatus.OK) {
            ManagerEntity manager = new ManagerEntity();
            Optional<ManagerEntity> managers = managerRepository.findByManagerUsername(username);
            if (managers.isPresent()) {
                manager.setManagerUsername(managers.get().getManagerUsername());
                manager.setManagerPassword(managers.get().getManagerPassword());
                manager.setManagerFirstname(managers.get().getManagerFirstname());
                manager.setManagerLastname(managers.get().getManagerLastname());
                manager.setManagerOffice(managers.get().getManagerOffice());
                manager.setManagerFacebook(managers.get().getManagerFacebook());
                manager.setManagerLineid(managers.get().getManagerLineid());
                manager.setManagerPhone(managers.get().getManagerPhone());
                manager.setManagerImage(response.getBody().getImgPath());
                manager.setManagerStatus(managers.get().getManagerStatus());
                manager.setMid(managers.get().getMid());
                ManagerEntity result = managerRepository.save(manager);
                return result.getManagerUsername();
            }else return null;

        }else return null;

    }


    public ManagerEntity saveManager(ManagerEntity managerBody) {
        ManagerEntity manager = new ManagerEntity();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!findManager(managerBody.getManagerUsername()).isPresent() && !tenantRepository.findByTenantUsername(managerBody.getManagerUsername()).isPresent()) {
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
        } else return null;
    }

}
