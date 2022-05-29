package com.csproject.homealoneservice.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.csproject.homealoneservice.dto.UserDTO;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
import com.csproject.homealoneservice.service.FileUpload;
import com.csproject.homealoneservice.service.ManagerService;
import com.csproject.homealoneservice.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    ManagerService managerService;

    @Autowired
    TenantService tenantService;

    @Autowired
    FileUpload fileUpload;

    @GetMapping(value = "")
    public String test(){
        return "UserController";
    }
//    @PostMapping(value = "/upload/profile",
//            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<ManagerEntity> uploadProfile(@RequestParam("managerId") String managerId, @RequestParam("file") MultipartFile file){
//        List<MultipartFile> result = fileUpload.handleFileUpload(file);
//        if(!file.isEmpty()){
//            return new ResponseEntity(managerService.saveManagerProfile(managerId,file),HttpStatus.OK);
//        }else return new ResponseEntity("อัพโหลดไฟล์ไม่สำเร็จ", HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @PostMapping(value = "/upload/profile",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadProfile(@RequestParam("username") String username, @RequestParam("file") MultipartFile file){
        if (!username.isEmpty() && managerService.findManager(username).isPresent()){
            return new ResponseEntity<>(managerService.saveManagerProfile(username,file), HttpStatus.OK);
        }else if (!username.isEmpty() && tenantService.findTenant(username).isPresent()){
                return new ResponseEntity<>(tenantService.saveTenantProfile(username,file), HttpStatus.OK);
            }else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(value = "signin",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> signIn(@RequestBody UserDTO userDTO){
        System.out.println("Username:"+userDTO.getUsername());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!userDTO.getUsername().isEmpty() && managerService.findManager(userDTO.getUsername()).isPresent()){
            Optional<ManagerEntity> manager = managerService.findManager(userDTO.getUsername());
            UserDTO user = new UserDTO();
            user.setUsername(manager.get().getManagerUsername());
            user.setStatus(manager.get().getManagerStatus());
            user.setId(manager.get().getMid());
            if (passwordEncoder.matches(userDTO.getPassword(),manager.get().getManagerPassword())){
              String token = JWT.create()
                      .withClaim("id",user.getId())
                      .withClaim("status",user.getStatus())
                      .sign(algorithm);
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        }else if (tenantService.findTenant(userDTO.getUsername()).isPresent()){
            Optional<TenantEntity> tenant = tenantService.findTenant(userDTO.getUsername());
            UserDTO user = new UserDTO();
            user.setUsername(tenant.get().getTenantUsername());
            user.setStatus(tenant.get().getTenantStatus());
            user.setId(tenant.get().getTid());
            if (passwordEncoder.matches(userDTO.getPassword(),tenant.get().getTenantPassword())){
                String token = JWT.create()
                        .withClaim("id",user.getId())
                        .withClaim("status",user.getStatus())
                        .sign(algorithm);
                return new ResponseEntity<>(token, HttpStatus.OK);
            }

        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    public TenantEntity findTenantByUsername(String username){
//        TenantEntity tenant = tenantRepository.findByTenantUsername(username).get();
//        return  tenant;
//    }

    @PostMapping(value = "/check-username",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> checkUsername(@RequestParam("username") String username){
        if (!username.isEmpty() && managerService.findManager(username).isPresent()){
            Optional<ManagerEntity> manager = managerService.findManager(username);
            return new ResponseEntity<>(manager.get().getManagerUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }else if (tenantService.findTenant(username).isPresent()){
            Optional<TenantEntity> tenant = tenantService.findTenant(username);
            return new ResponseEntity<>(tenant.get().getTenantUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping(value = "/forgot-password",
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> forgotPassword(@RequestParam("username") String username,@RequestParam("password") String newPassword){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!username.isEmpty() && managerService.findManager(username).isPresent()){
            ManagerEntity manager = managerService.findManager(username).get();
            manager.setManagerPassword(passwordEncoder.encode(newPassword));
            return new ResponseEntity<>(manager.getManagerUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        }else if (tenantService.findTenant(username).isPresent()){
            TenantEntity tenant = tenantService.findTenant(username).get();
            tenant.setTenantPassword(passwordEncoder.encode(newPassword));
            return new ResponseEntity<>(tenant.getTenantUsername(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

//        tenant.setTenantPassword(passwordEncoder.encode(newPassword));
//        return tenantRepository.save(tenant);
    }

}
