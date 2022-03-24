package com.csproject.homealoneservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews_image", schema = "comsci_homealone", catalog = "")
public class ReviewsImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid")
    private int pid;
    @Basic
    @Column(name = "rid")
    private int rid;
    @Basic
    @Column(name = "reviews_image")
    private String reviewsImage;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getReviewsImage() {
        return reviewsImage;
    }

    public void setReviewsImage(String reviewsImage) {
        this.reviewsImage = reviewsImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewsImageEntity that = (ReviewsImageEntity) o;

        if (pid != that.pid) return false;
        if (rid != that.rid) return false;
        if (reviewsImage != null ? !reviewsImage.equals(that.reviewsImage) : that.reviewsImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + rid;
        result = 31 * result + (reviewsImage != null ? reviewsImage.hashCode() : 0);
        return result;
    }
}
