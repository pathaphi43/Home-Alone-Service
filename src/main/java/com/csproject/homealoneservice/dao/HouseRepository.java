package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Integer>,HouseSpecification<HouseEntity> {


    List<HouseEntity> findByHouseNameLike(String name);

//    @Query("SELECT p FROM house p JOIN FETCH p.roles WHERE p.id = (:id)")
//    public HouseEntity findByIdAndFetchRolesEagerly(@Param("id") Long id);



    List<HouseEntity> findByHouseNameContainingIgnoreCase(String name);

    List<HouseEntity> findAll(Specification<HouseEntity> specification);

    List<HouseEntity> findAllByHouseName(String name);

    List<HouseEntity> findByHouseNameAndAndHouseProvinceAndAndHouseDistrict(String name, String province, String district);
}
