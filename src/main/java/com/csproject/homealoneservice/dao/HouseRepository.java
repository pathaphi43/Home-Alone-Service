package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Integer> {

}
