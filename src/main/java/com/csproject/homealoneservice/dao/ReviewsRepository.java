package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity,Integer> {

    List<ReviewsEntity> findByRid(Integer id);

    List<ReviewsEntity> findAllByTid(Integer id);
}
