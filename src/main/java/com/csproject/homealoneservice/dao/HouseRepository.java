package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Integer>,HouseSpecification<HouseEntity> {


    List<HouseEntity> findByHouseNameLike(String name);

    List<HouseEntity> findByHouseNameContainingIgnoreCase(String name);

    List<HouseEntity> findAll(Specification<HouseEntity> specification);

    List<HouseEntity> findAllByHouseName(String name);

    List<HouseEntity> findByHouseNameAndAndHouseProvinceAndAndHouseDistrict(String name, String province, String district);
}
