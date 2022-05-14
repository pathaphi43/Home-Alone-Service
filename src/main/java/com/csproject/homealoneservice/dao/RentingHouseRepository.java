package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.RentingHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface RentingHouseRepository extends JpaRepository<RentingHouseEntity,Integer> {

    List<RentingHouseEntity> findByHid(Integer id);
    RentingHouseEntity findByHidAndRentingStatus(Integer hid, Integer status);
}
