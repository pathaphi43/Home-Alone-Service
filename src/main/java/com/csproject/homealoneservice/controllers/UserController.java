package com.csproject.homealoneservice.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.csproject.homealoneservice.dto.UserDTO;
import com.csproject.homealoneservice.entity.ManagerEntity;
import com.csproject.homealoneservice.entity.TenantEntity;
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

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    ManagerService managerService;

    @Autowired
    TenantService tenantService;

    @GetMapping(value = "")
    public String test(){
        return "UserController";
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
            if (passwordEncoder.matches(userDTO.getPassword(),manager.get().getManagerPassword())){
              String token = JWT.create()
                      .withClaim("id",user.getUsername())
                      .withClaim("status",user.getStatus())
                      .sign(algorithm);
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
        }else if (tenantService.findTenant(userDTO.getUsername()).isPresent()){
            Optional<TenantEntity> tenant = tenantService.findTenant(userDTO.getUsername());
            UserDTO user = new UserDTO();
            user.setUsername(tenant.get().getTenantUsername());
            user.setStatus(tenant.get().getTenantStatus());
            if (passwordEncoder.matches(userDTO.getPassword(),tenant.get().getTenantPassword())){
                String token = JWT.create()
                        .withClaim("id",user.getUsername())
                        .withClaim("status",user.getStatus())
                        .sign(algorithm);
                return new ResponseEntity<>(token, HttpStatus.OK);
            }

        } else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
