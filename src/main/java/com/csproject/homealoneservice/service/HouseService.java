package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.HouseSpecification;
import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dao.RentalHouseImageRepository;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentalHouseImageEntity;
import com.csproject.homealoneservice.enumeration.HouseEnum;
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

    public List<HouseDTO> queryAllHouseAndImageAndStatus(){
        List<HouseDTO> houseDTO = new ArrayList<>();
        List<HouseEntity> houses = houseRepository.findAllByHouseStatusIs(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        for (HouseEntity house:houses){
            houseDTO.add(new HouseDTO(house.getHid(),house.getMid(),house.getHouseName(),house.getHouseAddress(),house.getHouseProvince(),house.getHouseDistrict(),house.getHouseZipcode(),
                    house.getHouseImage(),house.getHouseType(),house.getHouseFloors(),house.getHouseBedroom(),house.getHouseBathroom(),house.getHouseLivingroom(),house.getHouseKitchen(),house.getHouseArea(),
                    house.getHouseLatitude(),house.getHouseLongitude(),house.getHouseElectric(),house.getHouseWater(),house.getHouseRent(),house.getHouseDeposit(),house.getHouseInsurance(),house.getHouseStatus(),
                    rentalHouseImageRepository.findByHid(house.getHid())));
        }
        return houseDTO;
    }


    public  HouseEntity insertHouse(HouseEntity houseBody){
        HouseEntity house = new HouseEntity();
        house.setMid(houseBody.getMid());
        house.setHouseName(houseBody.getHouseName());
        house.setHouseAddress(houseBody.getHouseAddress());
        house.setHouseProvince(houseBody.getHouseProvince());
        house.setHouseDistrict(houseBody.getHouseDistrict());
        house.setHouseZipcode(house.getHouseZipcode());
        house.setHouseImage(HouseEnum.HOUSE_FIRST_INSERT.getImagePath());
        house.setHouseType(houseBody.getHouseType());
        house.setHouseFloors(houseBody.getHouseFloors());
        house.setHouseBedroom(houseBody.getHouseBedroom());
        house.setHouseBathroom(houseBody.getHouseBathroom());
        house.setHouseLivingroom(houseBody.getHouseLivingroom());
        house.setHouseKitchen(houseBody.getHouseKitchen());
        house.setHouseArea(houseBody.getHouseArea());
        house.setHouseLatitude(houseBody.getHouseLatitude());
        house.setHouseLongitude(houseBody.getHouseLongitude());
        house.setHouseElectric(houseBody.getHouseElectric());
        house.setHouseWater(houseBody.getHouseWater());
        house.setHouseRent(houseBody.getHouseRent());
        house.setHouseDeposit(houseBody.getHouseDeposit());
        house.setHouseInsurance(houseBody.getHouseInsurance());
        house.setHouseStatus(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        return houseRepository.save(house);
    }


    public HouseDTO queryHouseAndImage(Integer id){
        HouseDTO houseDTO = new HouseDTO();
        Optional<HouseEntity> houses = findById(id);
        if(houses.isPresent()){
            houseDTO.setHid(houses.get().getHid());
            houseDTO.setMid(houses.get().getMid());
            houseDTO.setHouseName(houses.get().getHouseName());
            houseDTO.setHouseArea(houses.get().getHouseArea());
            houseDTO.setHouseAddress(houses.get().getHouseAddress());
            houseDTO.setHouseImage(houses.get().getHouseImage());
            houseDTO.setHouseBathroom(houses.get().getHouseBedroom());
            houseDTO.setHouseBedroom(houses.get().getHouseBedroom());
            houseDTO.setHouseProvince(houses.get().getHouseProvince());
            houseDTO.setHouseDistrict(houses.get().getHouseDistrict());
            houseDTO.setHouseZipcode(houses.get().getHouseZipcode());
            houseDTO.setHouseFloors(houses.get().getHouseFloors());
            houseDTO.setHouseKitchen(houses.get().getHouseKitchen());
            houseDTO.setHouseLivingroom(houses.get().getHouseLivingroom());
            houseDTO.setHouseType(houses.get().getHouseType());
            houseDTO.setHouseWater(houses.get().getHouseWater());
            houseDTO.setHouseElectric(houses.get().getHouseElectric());
            houseDTO.setHouseLatitude(houses.get().getHouseLatitude());
            houseDTO.setHouseLongitude(houses.get().getHouseLongitude());
            houseDTO.setHouseRent(houses.get().getHouseRent());
            houseDTO.setHouseInsurance(houses.get().getHouseInsurance());
            houseDTO.setHouseDeposit(houses.get().getHouseDeposit());
            houseDTO.setHouseStatus(houses.get().getHouseStatus());
            houseDTO.setHouseImageList(rentalHouseImageRepository.findByHid(houses.get().getHid()));
            return houseDTO;
        }else return null;

    }



    public Optional<HouseEntity> findById(Integer id){return houseRepository.findById(id);}

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
