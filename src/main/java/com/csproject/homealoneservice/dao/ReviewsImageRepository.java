package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.ReviewsImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsImageRepository extends JpaRepository<ReviewsImageEntity,Integer> {

    List<ReviewsImageEntity> findByRid(Integer id);
}
