package com.csproject.homealoneservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "rental_house_image", schema = "comsci_homealone", catalog = "")
public class RentalHouseImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pid")
    private int pid;
    @Basic
    @Column(name = "hid")
    private int hid;
    @Basic
    @Column(name = "image_house_path")
    private String imageHousePath;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getImageHousePath() {
        return imageHousePath;
    }

    public void setImageHousePath(String imageHousePath) {
        this.imageHousePath = imageHousePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalHouseImageEntity that = (RentalHouseImageEntity) o;

        if (pid != that.pid) return false;
        if (hid != that.hid) return false;
        if (imageHousePath != null ? !imageHousePath.equals(that.imageHousePath) : that.imageHousePath != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + hid;
        result = 31 * result + (imageHousePath != null ? imageHousePath.hashCode() : 0);
        return result;
    }
}
