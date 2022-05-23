package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import com.csproject.homealoneservice.entity.PaymentEntity;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.Date;

public interface PaymentSpecification<T> {


    static Specification<PaymentEntity> dateBetween(Date dateFrom,  Date dateTo) {
        return (root, cq, cb) -> cb.between(root.get("installment"), dateFrom, dateTo);
    }

//    static Specification<PaymentEntity> dateBetweenBymid(int rid,Date dateFrom,  Date dateTo) {
//        return (root, cq, cb) -> {
//           if(cb.equal(root.get("rid"),rid).){
//
//           }
//        }
//    }
//    static Specification<MIRTransaction> idEqual(@NotNull Long id) {
//        return (root, cq, cb) -> cb.equal(root.get("id"), id);
//    }
}
