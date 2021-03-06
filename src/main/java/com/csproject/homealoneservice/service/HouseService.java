package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.*;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.dto.UploadFileDTO;
import com.csproject.homealoneservice.dto.UploadMultipartFileDTO;
import com.csproject.homealoneservice.entity.*;
import com.csproject.homealoneservice.enumeration.HouseEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.awt.print.Pageable;
import java.lang.management.MemoryUsage;
import java.util.*;

@Service
public class HouseService {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    HouseRepository houseRepository;

    @Autowired
    RentalHouseImageRepository rentalHouseImageRepository;

    @Autowired
    FileUpload fileUpload;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<HouseEntity> findOrderedByHidLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM HouseEntity p ORDER BY p.hid",
                HouseEntity.class).setMaxResults(limit).getResultList();
    }

    public Map<String, String> test() {
        HouseEntity house = new HouseEntity();
        ManagerEntity manager = new ManagerEntity();
        house.setHouseAddress("sadasd");
        house.setHouseName("dasgadsg");
        manager.setManagerPassword("fgsdgsfdg");
        List<Object> objects = new ArrayList<>();
        objects.add(house);
        objects.add(manager);
        return getObjectAsString(objects);
    }

    public Map<String, String> getObjectAsString(List<Object> objects) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> map = new HashMap<>();
            for (Object o : objects) {
                Table table = o.getClass().getAnnotation(Table.class);
                String s = mapper.writeValueAsString(o);
                map.put(table.name(), s);
            }
            BankAccountEntity bankAccountEntity = new BankAccountEntity();
            bankAccountEntity.setBankName(map.toString());
            bankAccountEntity.setMid(1);
            bankAccountRepository.save(bankAccountEntity);
            return map;
        } catch (Exception e) {
            return null;
        }
    }

    public HouseEntity findByHouseName(String name) {
        return houseRepository.findTopByHouseName(name);
    }

    public List<HouseDTO> queryAllHouseAndImage() {
        List<HouseDTO> houseDTO = new ArrayList<>();
        Iterable<HouseEntity> houses = queryAllHouse();
        for (HouseEntity house : houses) {
            houseDTO.add(new HouseDTO(house.getHid(), house.getMid(), house.getHouseName(), house.getHouseAddress(), house.getHouseProvince(), house.getHouseDistrict(), house.getHouseZipcode(),
                    house.getHouseImage(), house.getHouseType(), house.getHouseFloors(), house.getHouseBedroom(), house.getHouseBathroom(), house.getHouseLivingroom(), house.getHouseKitchen(), house.getHouseArea(),
                    house.getHouseLatitude(), house.getHouseLongitude(), house.getHouseElectric(), house.getHouseWater(), house.getHouseRent(), house.getHouseDeposit(), house.getHouseInsurance(), house.getHouseStatus(),
                    rentalHouseImageRepository.findByHid(house.getHid())));
        }
        return houseDTO;
    }

    public List<HouseDTO> queryAllHouseAndImageAndStatus() {
        List<HouseDTO> houseDTO = new ArrayList<>();
        List<HouseEntity> houses = houseRepository.findAllByHouseStatusIsOrderByHidDesc(HouseEnum.HOUSE_FIRST_INSERT.getStatus());
        for (HouseEntity house : houses) {
            houseDTO.add(new HouseDTO(house.getHid(), house.getMid(), house.getHouseName(), house.getHouseAddress(), house.getHouseProvince(), house.getHouseDistrict(), house.getHouseZipcode(),
                    house.getHouseImage(), house.getHouseType(), house.getHouseFloors(), house.getHouseBedroom(), house.getHouseBathroom(), house.getHouseLivingroom(), house.getHouseKitchen(), house.getHouseArea(),
                    house.getHouseLatitude(), house.getHouseLongitude(), house.getHouseElectric(), house.getHouseWater(), house.getHouseRent(), house.getHouseDeposit(), house.getHouseInsurance(), house.getHouseStatus(),
                    rentalHouseImageRepository.findByHid(house.getHid())));
        }
        return houseDTO;
    }

    public UploadMultipartFileDTO saveImage(int hid, MultipartFile[] file) {
        ResponseEntity<UploadMultipartFileDTO> response = fileUpload.uploadMultipartFile(file, hid);
        if (response != null) {
            RentalHouseImageEntity houseImage;
            for (String path : response.getBody().getImgPath()) {
                houseImage = new RentalHouseImageEntity();
                houseImage.setImageHousePath(path);
                houseImage.setHid(hid);
                rentalHouseImageRepository.save(houseImage);
            }
        }
        return response.getBody();
    }

    public HouseDTO queryHouseAndImageByhid(Integer hid) {
        HouseEntity house = houseRepository.findById(hid).get();
        HouseDTO houseDTO = new HouseDTO(house.getHid(), house.getMid(), house.getHouseName(), house.getHouseAddress(), house.getHouseProvince(), house.getHouseDistrict(), house.getHouseZipcode(),
                house.getHouseImage(), house.getHouseType(), house.getHouseFloors(), house.getHouseBedroom(), house.getHouseBathroom(), house.getHouseLivingroom(), house.getHouseKitchen(), house.getHouseArea(),
                house.getHouseLatitude(), house.getHouseLongitude(), house.getHouseElectric(), house.getHouseWater(), house.getHouseRent(), house.getHouseDeposit(), house.getHouseInsurance(), house.getHouseStatus(),
                rentalHouseImageRepository.findByHid(house.getHid()));
        return houseDTO;
    }

    public void deleteImageByPid(int pid) {
        rentalHouseImageRepository.deleteById(pid);
    }

    public List<HouseDTO> queryAllHouseAndImageAndStatusIs(Integer mid, Integer status) {
        List<HouseDTO> houseDTO = new ArrayList<>();
        List<HouseEntity> houses = houseRepository.findAllByMidAndHouseStatusIs(mid, status);
        for (HouseEntity house : houses) {
            houseDTO.add(new HouseDTO(house.getHid(), house.getMid(), house.getHouseName(), house.getHouseAddress(), house.getHouseProvince(), house.getHouseDistrict(), house.getHouseZipcode(),
                    house.getHouseImage(), house.getHouseType(), house.getHouseFloors(), house.getHouseBedroom(), house.getHouseBathroom(), house.getHouseLivingroom(), house.getHouseKitchen(), house.getHouseArea(),
                    house.getHouseLatitude(), house.getHouseLongitude(), house.getHouseElectric(), house.getHouseWater(), house.getHouseRent(), house.getHouseDeposit(), house.getHouseInsurance(), house.getHouseStatus(),
                    rentalHouseImageRepository.findByHid(house.getHid())));
        }
        return houseDTO;
    }

    public HouseEntity insertHouse(HouseEntity houseBody, @Nullable MultipartFile file) {
        HouseEntity house = new HouseEntity();
        house.setMid(houseBody.getMid());
        house.setHouseName(houseBody.getHouseName());
        house.setHouseAddress(houseBody.getHouseAddress());
        house.setHouseProvince(houseBody.getHouseProvince());
        house.setHouseDistrict(houseBody.getHouseDistrict());
        house.setHouseZipcode(house.getHouseZipcode());
        ResponseEntity<UploadFileDTO> response = null;
        if (file != null) {
            response = fileUpload.uploadProfile(file);
        }
        if (response != null) house.setHouseImage(response.getBody().getImgPath());
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
        return houseRepository.save(house);
    }

    public HouseEntity editHouse(HouseEntity houseBody, @Nullable MultipartFile file) {
        HouseEntity house = new HouseEntity();
        house.setHid(houseBody.getHid());
        house.setMid(houseBody.getMid());
        house.setHouseName(houseBody.getHouseName());
        house.setHouseAddress(houseBody.getHouseAddress());
        house.setHouseProvince(houseBody.getHouseProvince());
        house.setHouseDistrict(houseBody.getHouseDistrict());
        house.setHouseZipcode(house.getHouseZipcode());
        ResponseEntity<UploadFileDTO> response = null;
        if (file != null) {
            response = fileUpload.uploadProfile(file);
        }
        if (response != null) house.setHouseImage(response.getBody().getImgPath());
        else house.setHouseImage(houseBody.getHouseImage());
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
        house.setHouseStatus(houseBody.getHouseStatus());
        return houseRepository.save(house);
    }

    public HouseDTO queryHouseAndImage(Integer id) {
        HouseDTO houseDTO = new HouseDTO();
        Optional<HouseEntity> houses = findById(id);
        if (houses.isPresent()) {
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
        } else return null;
    }

    public Optional<HouseEntity> findById(Integer id) {
        return houseRepository.findById(id);
    }

    public List<HouseEntity> findByHouseId(Integer id) {
        return houseRepository.findByMid(id);
    }

    public Iterable<HouseEntity> queryAllHouse() {
        return houseRepository.findAll();
    }

    public List<HouseEntity> findAllHouseByname(String name) {
        return houseRepository.findAll(HouseSpecification.likeHouseName(name));
    }

    public List<HouseEntity> findAllProvinceAndAmphureLike(String province, String amphure) {
        return houseRepository.findAll(HouseSpecification.likeprovinceOramphure(province, amphure));
    }

    public List<HouseEntity> findAllProvinceAndAmphure(String province, String amphure) {
        return houseRepository.findAllByHouseProvinceAndHouseDistrict(province, amphure);
    }

    public List<HouseEntity> findAllByhouseNameProvinceAmphureStatus(String houseName, String province, String district, List<Integer> status) {
        return houseRepository.findAll(HouseSpecification.houseNameLikeAndProvinceAndAmphureAndStatus(houseName, province, district, status));
    }

    public List<HouseEntity> findAllByhouseNameProvinceStatus(String houseName, String province, List<Integer> status) {
        return houseRepository.findAll(HouseSpecification.houseNameLikeAndProvinceAndStatus(houseName, province, status));
    }


    public List<HouseEntity> findAllByhouseNameAndStatusIn(String houseName, List<Integer> status) {
        return houseRepository.findAll(HouseSpecification.houseNameLike(houseName, status));
    }


    public List<HouseEntity> findAllByHouseStatusIs(int status) {
        return houseRepository.findAllByHouseStatusIsOrderByHidDesc(status);
    }

    public List<HouseEntity> findAllByHouseStatusIsNot(int status) {
        return houseRepository.findAllByHouseStatusIsNot(status);
    }

    public HouseEntity dismissHouseByHid(Integer hid) {
        HouseEntity house = houseRepository.findById(hid).get();
        house.setHouseStatus(HouseEnum.House_Dismiss.getStatus());
        return houseRepository.save(house);
    }

    public void deleteHouseByHid(Integer hid) {
        houseRepository.deleteById(hid);
    }


    public HouseEntity rentHouse(Integer id) {
        HouseEntity house = new HouseEntity();
        house.setHid(id);
        Optional<HouseEntity> houseEntity = findById(id);
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

//    public List<HouseEntity>


}
