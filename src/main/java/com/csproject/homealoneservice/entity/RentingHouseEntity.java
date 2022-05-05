package com.csproject.homealoneservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "renting_house", schema = "comsci_homealone", catalog = "")
public class RentingHouseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rid")
    private int rid;
    @Basic
    @Column(name = "tid")
    private int tid;
    @Basic
    @Column(name = "hid")
    private int hid;
    @Basic
    @Column(name = "renting_book")
    private Timestamp rentingBook;
    @Basic
    @Column(name = "renting_check_in")
    private Timestamp rentingCheckIn;
    @Basic
    @Column(name = "renting_check_out")
    private Timestamp rentingCheckOut;
    @Basic
    @Column(name = "renting_image")
    private String rentingImage;
    @Basic
    @Column(name = "renting_status")
    private int rentingStatus;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public Timestamp getRentingBook() {
        return rentingBook;
    }

    public void setRentingBook(Timestamp rentingBook) {
        this.rentingBook = rentingBook;
    }

    public Timestamp getRentingCheckIn() {
        return rentingCheckIn;
    }

    public void setRentingCheckIn(Timestamp rentingCheckIn) {
        this.rentingCheckIn = rentingCheckIn;
    }

    public Timestamp getRentingCheckOut() {
        return rentingCheckOut;
    }

    public void setRentingCheckOut(Timestamp rentingCheckOut) {
        this.rentingCheckOut = rentingCheckOut;
    }

    public String getRentingImage() {
        return rentingImage;
    }

    public void setRentingImage(String rentingImage) {
        this.rentingImage = rentingImage;
    }

    public int getRentingStatus() {
        return rentingStatus;
    }

    public void setRentingStatus(int rentingStatus) {
        this.rentingStatus = rentingStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentingHouseEntity that = (RentingHouseEntity) o;

        if (rid != that.rid) return false;
        if (tid != that.tid) return false;
        if (hid != that.hid) return false;
        if (rentingBook != null ? !rentingBook.equals(that.rentingBook) : that.rentingBook != null) return false;
        if (rentingCheckIn != null ? !rentingCheckIn.equals(that.rentingCheckIn) : that.rentingCheckIn != null)
            return false;
        if (rentingCheckOut != null ? !rentingCheckOut.equals(that.rentingCheckOut) : that.rentingCheckOut != null)
            return false;
        if (rentingImage != null ? !rentingImage.equals(that.rentingImage) : that.rentingImage != null) return false;

        if (rentingStatus != that.rentingStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + tid;
        result = 31 * result + hid;
        result = 31 * result + (rentingBook != null ? rentingBook.hashCode() : 0);
        result = 31 * result + (rentingCheckIn != null ? rentingCheckIn.hashCode() : 0);
        result = 31 * result + (rentingCheckOut != null ? rentingCheckOut.hashCode() : 0);
        result = 31 * result + (rentingImage != null ? rentingImage.hashCode() : 0);
        result = 31 * result + rentingStatus;
        return result;
    }
}
