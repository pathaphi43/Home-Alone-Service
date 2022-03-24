package com.csproject.homealoneservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reviews", schema = "comsci_homealone", catalog = "")
public class ReviewsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "rid")
    private Integer rid;
    @Basic
    @Column(name = "tid")
    private Integer tid;
    @Basic
    @Column(name = "reviews_text")
    private String reviewsText;
    @Basic
    @Column(name = "reviews_score")
    private Integer reviewsScore;
    @Basic
    @Column(name = "reviews_date")
    private Timestamp reviewsDate;
    @Basic
    @Column(name = "reviews_status")
    private Integer reviewsStatus;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewsEntity that = (ReviewsEntity) o;

        if (id != that.id) return false;
        if (rid != null ? !rid.equals(that.rid) : that.rid != null) return false;
        if (tid != null ? !tid.equals(that.tid) : that.tid != null) return false;
        if (reviewsText != null ? !reviewsText.equals(that.reviewsText) : that.reviewsText != null) return false;
        if (reviewsScore != null ? !reviewsScore.equals(that.reviewsScore) : that.reviewsScore != null) return false;
        if (reviewsDate != null ? !reviewsDate.equals(that.reviewsDate) : that.reviewsDate != null) return false;
        if (reviewsStatus != null ? !reviewsStatus.equals(that.reviewsStatus) : that.reviewsStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rid != null ? rid.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (reviewsText != null ? reviewsText.hashCode() : 0);
        result = 31 * result + (reviewsScore != null ? reviewsScore.hashCode() : 0);
        result = 31 * result + (reviewsDate != null ? reviewsDate.hashCode() : 0);
        result = 31 * result + (reviewsStatus != null ? reviewsStatus.hashCode() : 0);
        return result;
    }
}
