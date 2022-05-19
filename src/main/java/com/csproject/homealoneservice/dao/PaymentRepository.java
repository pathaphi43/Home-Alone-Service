package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.PaymentEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer>,PaymentSpecification<PaymentEntity> {

  List<PaymentEntity> findAllByRid(Integer rid);

  List<PaymentEntity> findAll(Specification<PaymentEntity> specification);

//  List<PaymentEntity> findAllByAndInstallmentBetween();
}
