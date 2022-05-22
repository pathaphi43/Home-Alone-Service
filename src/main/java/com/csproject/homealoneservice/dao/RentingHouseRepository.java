package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.RentingHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface RentingHouseRepository extends JpaRepository<RentingHouseEntity,Integer> {

    List<RentingHouseEntity> findByHid(Integer id);
    RentingHouseEntity findByHidAndRentingStatus(Integer hid, Integer status);
    List<RentingHouseEntity> findAllByTidAndRentingStatus(Integer tid,Integer status);
    List<RentingHouseEntity> findAllByHid(Integer hid);
    List<RentingHouseEntity> findAllByHidAndRentingStatusIn(Integer hid,List<Integer> status);
    List<RentingHouseEntity> findAllByTidAndRentingStatusIn(Integer tid,List<Integer> status);
}
