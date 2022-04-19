package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.data.jpa.domain.Specification;

public interface HouseSpecification<T> {
    static Specification<HouseEntity> likeHouseName(String houseName) {
        return (root, cq, cb) -> cb.like(root.get("houseName"), "%" + houseName + "%");
    }

//    static Specification<MIRTransaction> likeMidOrTid(@NotNull String midOrTid) {
//        return (root, cq, cb) ->
//                cb.or(
//                        cb.like(root.get("merchantId"), "%" + midOrTid + "%"),
//                        cb.like(root.get("terminalId"), "%" + midOrTid + "%")
//                );
//    }
}
