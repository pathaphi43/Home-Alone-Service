package com.csproject.homealoneservice.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payment", schema = "comsci_homealone", catalog = "")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "rid")
    private Integer rid;
    @Basic
    @Column(name = "installment")
    private Timestamp installment;
    @Basic
    @Column(name = "pay_house_amount")
    private int payHouseAmount;
    @Basic
    @Column(name = "pay_house_date")
    private Timestamp payHouseDate;
    @Basic
    @Column(name = "pay_house_end")
    private Timestamp payHouseEnd;
    @Basic
    @Column(name = "pay_house_img")
    private String payHouseImg;
    @Basic
    @Column(name = "pay_house_status")
    private int payHouseStatus;
    @Basic
    @Column(name = "pay_elec_inmonth")
    private Timestamp payElecInmonth;
    @Basic
    @Column(name = "pay_elec_amount")
    private String payElecAmount;
    @Basic
    @Column(name = "pay_elec_date")
    private Timestamp payElecDate;
    @Basic
    @Column(name = "pay_elec_end")
    private Timestamp payElecEnd;
    @Basic
    @Column(name = "pay_elec_img")
    private String payElecImg;
    @Basic
    @Column(name = "pay_elec_status")
    private int payElecStatus;
    @Basic
    @Column(name = "pay_water_inmonth")
    private Timestamp payWaterInmonth;
    @Basic
    @Column(name = "pay_water_amount")
    private String payWaterAmount;
    @Basic
    @Column(name = "pay_water_date")
    private Timestamp payWaterDate;
    @Basic
    @Column(name = "pay_water_end")
    private Timestamp payWaterEnd;
    @Basic
    @Column(name = "pay_water_img")
    private String payWaterImg;
    @Basic
    @Column(name = "pay_water_status")
    private int payWaterStatus;

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

    public Timestamp getInstallment() {
        return installment;
    }

    public void setInstallment(Timestamp installment) {
        this.installment = installment;
    }

    public int getPayHouseAmount() {
        return payHouseAmount;
    }

    public void setPayHouseAmount(int payHouseAmount) {
        this.payHouseAmount = payHouseAmount;
    }

    public Timestamp getPayHouseDate() {
        return payHouseDate;
    }

    public void setPayHouseDate(Timestamp payHouseDate) {
        this.payHouseDate = payHouseDate;
    }

    public Timestamp getPayHouseEnd() {
        return payHouseEnd;
    }

    public void setPayHouseEnd(Timestamp payHouseEnd) {
        this.payHouseEnd = payHouseEnd;
    }

    public String getPayHouseImg() {
        return payHouseImg;
    }

    public void setPayHouseImg(String payHouseImg) {
        this.payHouseImg = payHouseImg;
    }

    public int getPayHouseStatus() {
        return payHouseStatus;
    }

    public void setPayHouseStatus(int payHouseStatus) {
        this.payHouseStatus = payHouseStatus;
    }

    public Timestamp getPayElecInmonth() {
        return payElecInmonth;
    }

    public void setPayElecInmonth(Timestamp payElecInmonth) {
        this.payElecInmonth = payElecInmonth;
    }

    public String getPayElecAmount() {
        return payElecAmount;
    }

    public void setPayElecAmount(String payElecAmount) {
        this.payElecAmount = payElecAmount;
    }

    public Timestamp getPayElecDate() {
        return payElecDate;
    }

    public void setPayElecDate(Timestamp payElecDate) {
        this.payElecDate = payElecDate;
    }

    public Timestamp getPayElecEnd() {
        return payElecEnd;
    }

    public void setPayElecEnd(Timestamp payElecEnd) {
        this.payElecEnd = payElecEnd;
    }

    public String getPayElecImg() {
        return payElecImg;
    }

    public void setPayElecImg(String payElecImg) {
        this.payElecImg = payElecImg;
    }

    public int getPayElecStatus() {
        return payElecStatus;
    }

    public void setPayElecStatus(int payElecStatus) {
        this.payElecStatus = payElecStatus;
    }

    public Timestamp getPayWaterInmonth() {
        return payWaterInmonth;
    }

    public void setPayWaterInmonth(Timestamp payWaterInmonth) {
        this.payWaterInmonth = payWaterInmonth;
    }

    public String getPayWaterAmount() {
        return payWaterAmount;
    }

    public void setPayWaterAmount(String payWaterAmount) {
        this.payWaterAmount = payWaterAmount;
    }

    public Timestamp getPayWaterDate() {
        return payWaterDate;
    }

    public void setPayWaterDate(Timestamp payWaterDate) {
        this.payWaterDate = payWaterDate;
    }

    public Timestamp getPayWaterEnd() {
        return payWaterEnd;
    }

    public void setPayWaterEnd(Timestamp payWaterEnd) {
        this.payWaterEnd = payWaterEnd;
    }

    public String getPayWaterImg() {
        return payWaterImg;
    }

    public void setPayWaterImg(String payWaterImg) {
        this.payWaterImg = payWaterImg;
    }

    public int getPayWaterStatus() {
        return payWaterStatus;
    }

    public void setPayWaterStatus(int payWaterStatus) {
        this.payWaterStatus = payWaterStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (id != that.id) return false;
        if (payHouseAmount != that.payHouseAmount) return false;
        if (payHouseStatus != that.payHouseStatus) return false;
        if (payElecAmount != null ? !payElecAmount.equals(that.payElecAmount) : that.payElecAmount != null) return false;
        if (payElecStatus != that.payElecStatus) return false;
        if (payWaterAmount != null ? !payWaterAmount.equals(that.payWaterAmount) : that.payWaterAmount != null) return false;
        if (payWaterStatus != that.payWaterStatus) return false;
        if (rid != null ? !rid.equals(that.rid) : that.rid != null) return false;
        if (installment != null ? !installment.equals(that.installment) : that.installment != null) return false;
        if (payHouseDate != null ? !payHouseDate.equals(that.payHouseDate) : that.payHouseDate != null) return false;
        if (payHouseEnd != null ? !payHouseEnd.equals(that.payHouseEnd) : that.payHouseEnd != null) return false;
        if (payHouseImg != null ? !payHouseImg.equals(that.payHouseImg) : that.payHouseImg != null) return false;
        if (payElecInmonth != null ? !payElecInmonth.equals(that.payElecInmonth) : that.payElecInmonth != null)
            return false;
        if (payElecDate != null ? !payElecDate.equals(that.payElecDate) : that.payElecDate != null) return false;
        if (payElecEnd != null ? !payElecEnd.equals(that.payElecEnd) : that.payElecEnd != null) return false;
        if (payElecImg != null ? !payElecImg.equals(that.payElecImg) : that.payElecImg != null) return false;
        if (payWaterInmonth != null ? !payWaterInmonth.equals(that.payWaterInmonth) : that.payWaterInmonth != null)
            return false;
        if (payWaterDate != null ? !payWaterDate.equals(that.payWaterDate) : that.payWaterDate != null) return false;
        if (payWaterEnd != null ? !payWaterEnd.equals(that.payWaterEnd) : that.payWaterEnd != null) return false;
        if (payWaterImg != null ? !payWaterImg.equals(that.payWaterImg) : that.payWaterImg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (rid != null ? rid.hashCode() : 0);
        result = 31 * result + (installment != null ? installment.hashCode() : 0);
        result = 31 * result + payHouseAmount;
        result = 31 * result + (payHouseDate != null ? payHouseDate.hashCode() : 0);
        result = 31 * result + (payHouseEnd != null ? payHouseEnd.hashCode() : 0);
        result = 31 * result + (payHouseImg != null ? payHouseImg.hashCode() : 0);
        result = 31 * result + payHouseStatus;
        result = 31 * result + (payElecInmonth != null ? payElecInmonth.hashCode() : 0);
        result = 31 * result + (payElecAmount != null ? payElecAmount.hashCode() : 0);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (payElecDate != null ? payElecDate.hashCode() : 0);
        result = 31 * result + (payElecEnd != null ? payElecEnd.hashCode() : 0);
        result = 31 * result + (payElecImg != null ? payElecImg.hashCode() : 0);
        result = 31 * result + payElecStatus;
        result = 31 * result + (payWaterInmonth != null ? payWaterInmonth.hashCode() : 0);
        result = 31 * result + (payWaterAmount != null ? payWaterAmount.hashCode() : 0);
//    temp = Double.doubleToLongBits(payWaterAmount);
//        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (payWaterDate != null ? payWaterDate.hashCode() : 0);
        result = 31 * result + (payWaterEnd != null ? payWaterEnd.hashCode() : 0);
        result = 31 * result + (payWaterImg != null ? payWaterImg.hashCode() : 0);
        result = 31 * result + payWaterStatus;
        return result;
    }
}
