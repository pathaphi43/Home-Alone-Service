package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.HouseSpecification;
import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.RentalHouseImageRepository;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentalHouseImageEntity;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    RentalHouseImageRepository rentalHouseImageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<HouseEntity> findOrderedByHidLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM HouseEntity p ORDER BY p.hid",
                HouseEntity.class).setMaxResults(limit).getResultList();
    }

    public List<HouseDTO> queryAllHouseAndImage(){
        List<HouseDTO> houseDTO = new ArrayList<>();
        Iterable<HouseEntity> houses= queryAllHouse();
        for (HouseEntity house:houses){
           houseDTO.add(new HouseDTO(house.getHid(),house.getMid(),house.getHouseName(),house.getHouseAddress(),house.getHouseProvince(),house.getHouseDistrict(),house.getHouseZipcode(),
                   house.getHouseImage(),house.getHouseType(),house.getHouseFloors(),house.getHouseBedroom(),house.getHouseBathroom(),house.getHouseLivingroom(),house.getHouseKitchen(),house.getHouseArea(),
                   house.getHouseLatitude(),house.getHouseLongitude(),house.getHouseElectric(),house.getHouseWater(),house.getHouseRent(),house.getHouseDeposit(),house.getHouseInsurance(),house.getHouseStatus(),
                   rentalHouseImageRepository.findByHid(house.getHid())));
        }
        return houseDTO;
    }

    public List<HouseEntity> findByHouseId(Integer id){
        return houseRepository.findByMid(id);
    }


    public Iterable<HouseEntity> queryAllHouse() {
        return houseRepository.findAll();
    }

    public List<HouseEntity> findAllHouseByname(String name) {
        return houseRepository.findAll(HouseSpecification.likeHouseName(name));
    }

    //    Session session;
//    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
//    EntityManager em = emfactory.createEntityManager();
//
//    public HouseEntity findByHid(int id) {
//        return em.createQuery("SELECT u FROM HouseEntity AS u JOIN FETCH u.hid WHERE u.hid=:id", HouseEntity.class)
//                .setParameter("id", id).getSingleResult();
//    }
//
//    List<HouseEntity> users = session.createQuery("From UserLazy").list();
//    UserLazy userLazyLoaded = users.get(3);
//        return (userLazyLoaded.getOrderDetail());

//Pageable pageable = new OffsetBasedPageRequest(offset, limit);
//   return this.dataServices.findAllInclusive(pageable);
}
