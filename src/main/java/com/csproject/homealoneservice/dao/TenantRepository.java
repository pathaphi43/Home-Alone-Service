package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<TenantEntity,Integer> {


    Optional<TenantEntity> findByTenantUsername(String username);
}
