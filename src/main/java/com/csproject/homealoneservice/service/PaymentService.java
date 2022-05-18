package com.csproject.homealoneservice.service;

import com.csproject.homealoneservice.dao.PaymentRepository;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.csproject.homealoneservice.enumeration.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService{

    @Autowired
    PaymentRepository paymentRepository;

   public List<PaymentEntity> findAllPayment(){
        return paymentRepository.findAll();
   }

   public PaymentEntity savePayment(PaymentEntity paymentBody){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setRid(paymentBody.getRid());
        paymentEntity.setInstallment(paymentBody.getInstallment());
        paymentEntity.setPayHouseAmount(paymentBody.getPayHouseAmount());
        paymentEntity.setPayHouseEnd(paymentBody.getPayHouseEnd());
        paymentEntity.setPayHouseEnd(paymentBody.getPayHouseEnd());
        paymentEntity.setPayHouseStatus(StatusEnum.Prepare_Status.getStatus());
        paymentEntity.setPayElecAmount(paymentBody.getPayElecAmount());
        paymentEntity.setPayElecInmonth(paymentBody.getPayElecInmonth());
        paymentEntity.setPayElecEnd(paymentBody.getPayElecEnd());
        paymentEntity.setPayElecStatus(StatusEnum.Prepare_Status.getStatus());
        paymentEntity.setPayWaterAmount(paymentBody.getPayWaterAmount());
        paymentEntity.setPayWaterInmonth(paymentBody.getPayWaterInmonth());
        paymentEntity.setPayWaterEnd(paymentBody.getPayWaterEnd());
        paymentEntity.setPayWaterStatus(StatusEnum.Prepare_Status.getStatus());
       return paymentRepository.save(paymentEntity);
   }
}
