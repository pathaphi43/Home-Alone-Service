package com.csproject.homealoneservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "manager", schema = "comsci_homealone", catalog = "")
public class ManagerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mid")
    private int mid;
    @Basic
    @Column(name = "manager_username")
    private String managerUsername;
    @Basic
    @Column(name = "manager_password")
    private String managerPassword;
    @Basic
    @Column(name = "manager_firstname")
    private String managerFirstname;
    @Basic
    @Column(name = "manager_lastname")
    private String managerLastname;
    @Basic
    @Column(name = "manager_image")
    private String managerImage;
    @Basic
    @Column(name = "manager_phone")
    private String managerPhone;
    @Basic
    @Column(name = "manager_office")
    private String managerOffice;
    @Basic
    @Column(name = "manager_lineid")
    private String managerLineid;
    @Basic
    @Column(name = "manager_facebook")
    private String managerFacebook;
    @Basic
    @Column(name = "manager_status")
    private int managerStatus;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getManagerFirstname() {
        return managerFirstname;
    }

    public void setManagerFirstname(String managerFirstname) {
        this.managerFirstname = managerFirstname;
    }

    public String getManagerLastname() {
        return managerLastname;
    }

    public void setManagerLastname(String managerLastname) {
        this.managerLastname = managerLastname;
    }

    public String getManagerImage() {
        return managerImage;
    }

    public void setManagerImage(String managerImage) {
        this.managerImage = managerImage;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerOffice() {
        return managerOffice;
    }

    public void setManagerOffice(String managerOffice) {
        this.managerOffice = managerOffice;
    }

    public String getManagerLineid() {
        return managerLineid;
    }

    public void setManagerLineid(String managerLineid) {
        this.managerLineid = managerLineid;
    }

    public String getManagerFacebook() {
        return managerFacebook;
    }

    public void setManagerFacebook(String managerFacebook) {
        this.managerFacebook = managerFacebook;
    }

    public int getManagerStatus() {
        return managerStatus;
    }

    public void setManagerStatus(int managerStatus) {
        this.managerStatus = managerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerEntity that = (ManagerEntity) o;

        if (mid != that.mid) return false;
        if (managerStatus != that.managerStatus) return false;
        if (managerUsername != null ? !managerUsername.equals(that.managerUsername) : that.managerUsername != null)
            return false;
        if (managerPassword != null ? !managerPassword.equals(that.managerPassword) : that.managerPassword != null)
            return false;
        if (managerFirstname != null ? !managerFirstname.equals(that.managerFirstname) : that.managerFirstname != null)
            return false;
        if (managerLastname != null ? !managerLastname.equals(that.managerLastname) : that.managerLastname != null)
            return false;
        if (managerImage != null ? !managerImage.equals(that.managerImage) : that.managerImage != null) return false;
        if (managerPhone != null ? !managerPhone.equals(that.managerPhone) : that.managerPhone != null) return false;
        if (managerOffice != null ? !managerOffice.equals(that.managerOffice) : that.managerOffice != null)
            return false;
        if (managerLineid != null ? !managerLineid.equals(that.managerLineid) : that.managerLineid != null)
            return false;
        if (managerFacebook != null ? !managerFacebook.equals(that.managerFacebook) : that.managerFacebook != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mid;
        result = 31 * result + (managerUsername != null ? managerUsername.hashCode() : 0);
        result = 31 * result + (managerPassword != null ? managerPassword.hashCode() : 0);
        result = 31 * result + (managerFirstname != null ? managerFirstname.hashCode() : 0);
        result = 31 * result + (managerLastname != null ? managerLastname.hashCode() : 0);
        result = 31 * result + (managerImage != null ? managerImage.hashCode() : 0);
        result = 31 * result + (managerPhone != null ? managerPhone.hashCode() : 0);
        result = 31 * result + (managerOffice != null ? managerOffice.hashCode() : 0);
        result = 31 * result + (managerLineid != null ? managerLineid.hashCode() : 0);
        result = 31 * result + (managerFacebook != null ? managerFacebook.hashCode() : 0);
        result = 31 * result + managerStatus;
        return result;
    }
}
