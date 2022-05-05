package com.csproject.homealoneservice.dto;


import com.csproject.homealoneservice.entity.ReviewsImageEntity;

import java.sql.Timestamp;
import java.util.List;

public class ReviewsDTO {

    private int id;

    private Integer rid;

    private Integer tid;

    private String reviewsText;

    private Integer reviewsScore;

    private Timestamp reviewsDate;

    private Integer reviewsStatus;

    private List<ReviewsImageEntity> reviewsImage;

    public ReviewsDTO(int id, Integer rid, Integer tid, String reviewsText, Integer reviewsScore, Timestamp reviewsDate, Integer reviewsStatus, List<ReviewsImageEntity> reviewsImage) {
        this.id = id;
        this.rid = rid;
        this.tid = tid;
        this.reviewsText = reviewsText;
        this.reviewsScore = reviewsScore;
        this.reviewsDate = reviewsDate;
        this.reviewsStatus = reviewsStatus;
        this.reviewsImage = reviewsImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getReviewsText() {
        return reviewsText;
    }

    public void setReviewsText(String reviewsText) {
        this.reviewsText = reviewsText;
    }

    public Integer getReviewsScore() {
        return reviewsScore;
    }

    public void setReviewsScore(Integer reviewsScore) {
        this.reviewsScore = reviewsScore;
    }

    public Timestamp getReviewsDate() {
        return reviewsDate;
    }

    public void setReviewsDate(Timestamp reviewsDate) {
        this.reviewsDate = reviewsDate;
    }

    public Integer getReviewsStatus() {
        return reviewsStatus;
    }

    public void setReviewsStatus(Integer reviewsStatus) {
        this.reviewsStatus = reviewsStatus;
    }

    public List<ReviewsImageEntity> getReviewsImage() {
        return reviewsImage;
    }

    public void setReviewsImage(List<ReviewsImageEntity> reviewsImage) {
        this.reviewsImage = reviewsImage;
    }
}
