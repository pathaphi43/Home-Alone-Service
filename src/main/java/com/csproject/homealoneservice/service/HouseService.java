package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.*;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.RentalHouseImageEntity;
import com.csproject.homealoneservice.entity.RentingHouseEntity;
import com.csproject.homealoneservice.enumeration.HouseEnum;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    RentalHouseImageRepository rentalHouseImageRepository;

    @Autowired
    FileUpload fileUpload;

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

    public List<HouseDTO> queryAllHouseAndImageAndStatusIs(Integer mid,Integer status){
        List<HouseDTO> houseDTO = new ArrayList<>();
        List<HouseEntity> houses = houseRepository.findAllByMidAndHouseStatusIs(mid,status);
        for (HouseEntity house:houses){
            houseDTO.add(new HouseDTO(house.getHid(),house.getMid(),house.getHouseName(),house.getHouseAddress(),house.getHouseProvince(),house.getHouseDistrict(),house.getHouseZipcode(),
                    house.getHouseImage(),house.getHouseType(),house.getHouseFloors(),house.getHouseBedroom(),house.getHouseBathroom(),house.getHouseLivingroom(),house.getHouseKitchen(),house.getHouseArea(),
                    house.getHouseLatitude(),house.getHouseLongitude(),house.getHouseElectric(),house.getHouseWater(),house.getHouseRent(),house.getHouseDeposit(),house.getHouseInsurance(),house.getHouseStatus(),
                    rentalHouseImageRepository.findByHid(house.getHid())));
        }
        return houseDTO;
    }

    public  HouseEntity insertHouse(HouseEntity houseBody, @Nullable MultipartFile file){
        HouseEntity house = new HouseEntity();
        house.setMid(houseBody.getMid());
        house.setHouseName(houseBody.getHouseName());
        house.setHouseAddress(houseBody.getHouseAddress());
        house.setHouseProvince(houseBody.getHouseProvince());
        house.setHouseDistrict(houseBody.getHouseDistrict());
        house.setHouseZipcode(house.getHouseZipcode());
        ResponseEntity<UploadFileDTO> response = null ;
        if(file != null){
            response = fileUpload.uploadProfile(file);
        }
        if(response != null) house.setHouseImage(response.getBody().getImgPath());
        else house.setHouseImage(HouseEnum.HOUSE_FIRST_INSERT.getImagePath());
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
        return house;
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

    public HouseEntity rentHouse(Integer id){
        HouseEntity house = new HouseEntity();
        house.setHid(id);
        Optional<HouseEntity> houseEntity= findById(id);
        house.setMid(houseEntity.get().getMid());
        house.setHouseName(houseEntity.get().getHouseName());
        house.setHouseAddress(houseEntity.get().getHouseAddress());
        house.setHouseProvince(houseEntity.get().getHouseProvince());
        house.setHouseDistrict(houseEntity.get().getHouseDistrict());
        house.setHouseZipcode(houseEntity.get().getHouseZipcode());
        house.setHouseImage(houseEntity.get().getHouseImage());
        house.setHouseType(houseEntity.get().getHouseType());
        house.setHouseFloors(houseEntity.get().getHouseFloors());
        house.setHouseBedroom(houseEntity.get().getHouseBedroom());
        house.setHouseBathroom(houseEntity.get().getHouseBathroom());
        house.setHouseLivingroom(houseEntity.get().getHouseLivingroom());
        house.setHouseKitchen(houseEntity.get().getHouseKitchen());
        house.setHouseArea(houseEntity.get().getHouseArea());
        house.setHouseLatitude(houseEntity.get().getHouseLatitude());
        house.setHouseLongitude(houseEntity.get().getHouseLongitude());
        house.setHouseElectric(houseEntity.get().getHouseElectric());
        house.setHouseWater(houseEntity.get().getHouseWater());
        house.setHouseRent(houseEntity.get().getHouseRent());
        house.setHouseDeposit(houseEntity.get().getHouseDeposit());
        house.setHouseInsurance(houseEntity.get().getHouseInsurance());
        house.setHouseStatus(HouseEnum.HOUSE_RENT.getStatus());
        return houseRepository.save(house);
    }


}
