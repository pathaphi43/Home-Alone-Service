package com.csproject.homealoneservice.dao;

import com.csproject.homealoneservice.entity.HouseEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

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

    static Specification<HouseEntity> houseNameLike(String houseName, List<Integer> status) {
        return (root, cq, cb) ->
                cb.and(
                        cb.like(root.get("houseName"), "%" + houseName + "%"),
                        cb.in(root.get("houseStatus")).value(status)
                );

    }

    static Specification<HouseEntity> houseNameLikeAndProvinceAndStatus(String houseName,String province, List<Integer> status) {
        return (root, cq, cb) ->
            cb.and(
                    cb.like(root.get("houseName"), "%" + houseName + "%"),
                    cb.equal(root.get("houseProvince"), province),
                    cb.in(root.get("houseStatus")).value(status)
            );
    }


    static Specification<HouseEntity> houseNameLikeAndProvinceAndAmphureAndStatus(String houseName,String province,String amphure, List<Integer> status) {
        return (root, cq, cb) ->
                cb.and(
                        cb.like(root.get("houseName"), "%" + houseName + "%"),
                        cb.equal(root.get("houseProvince"), province),
                        cb.equal(root.get("houseDistrict"), amphure ),
                        cb.in(root.get("houseStatus")).value(status)
                );

    }
}
