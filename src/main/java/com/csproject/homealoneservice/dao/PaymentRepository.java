package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.PaymentEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer>,PaymentSpecification<PaymentEntity> {

  List<PaymentEntity> findAllByRid(Integer rid);

  List<PaymentEntity> findAll(Specification<PaymentEntity> specification);

  List<PaymentEntity> findAllByRidAndPayHouseStatusAndInstallmentBetweenOrderByInstallment(int rid,int status, Date dateFrom, Date dateTo);

//  List<PaymentEntity> findAllByAndInstallmentBetween();
//function('date_format',payment.installment,'%M')
  @Query("select house.houseName,SUM(payment.payHouseAmount)from PaymentEntity payment,HouseEntity house,RentingHouseEntity renting_house,ManagerEntity manager where manager.mid = ?1 AND house.mid = ?1 AND renting_house.hid = house.hid AND payment.rid = renting_house.rid AND payment.payHouseStatus = ?2 AND payment.installment BETWEEN  ?3  And  ?4 GROUP BY house.houseName")
  List<Object[]>  paymentSummary(int rid,int status, Date dateFrom, Date dateTo);
}
