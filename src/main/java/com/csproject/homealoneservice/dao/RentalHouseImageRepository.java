package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.RentalHouseImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalHouseImageRepository extends JpaRepository<RentalHouseImageEntity,Integer> {
    List<RentalHouseImageEntity> findByHid(int id);

}
