package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.HouseRepository;
import com.csproject.homealoneservice.dao.HouseSpecification;
import com.csproject.homealoneservice.dao.ManagerRepository;
import com.csproject.homealoneservice.dto.HouseDTO;
import com.csproject.homealoneservice.entity.HouseEntity;
import com.mysql.cj.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<HouseEntity> findOrderedByHidLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM HouseEntity p ORDER BY p.hid",
                HouseEntity.class).setMaxResults(limit).getResultList();
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

    public Iterable<HouseEntity> queryAllHouse() {
        return houseRepository.findAll();
    }

    public List<HouseEntity> findAllHouseByname(String name) {
        return houseRepository.findAll(HouseSpecification.likeHouseName(name));
    }
}
