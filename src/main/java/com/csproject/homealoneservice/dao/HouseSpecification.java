package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.data.jpa.domain.Specification;

public interface HouseSpecification<T> {
    static Specification<HouseEntity> likeHouseName(String houseName) {
        return (root, cq, cb) -> cb.like(root.get("houseName"), "%" + houseName + "%");
    }

    static Specification<HouseEntity> likeprovinceOramphure(String province,String amphure) {
        return (root, cq, cb) ->
                cb.or(
                        cb.like(root.get("houseProvince"), "%" + province + "%"),
                        cb.like(root.get("houseDistrict"), "%" + amphure + "%")
                );
    }
}
