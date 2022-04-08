package com.csproject.homealoneservice.dao;


import com.csproject.homealoneservice.entity.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerEntity,Integer> {


    Optional<ManagerEntity> findByManagerUsername(String username);
}
