package com.csproject.homealoneservice.dto;

import javax.persistence.Basic;
import javax.persistence.Column;

public class ManagerDTO {

    private int mid;

    private String managerUsername;

    private String managerPassword;

    private String managerFirstname;

    private String managerLastname;

    private String managerImage;

    private String managerPhone;

    private String managerOffice;

    private String managerLineid;

    private String managerFacebook;

    private int managerStatus;

    public ManagerDTO() {

    }

    //Register
    public ManagerDTO(String managerUsername, String managerPassword, String managerFirstname, String managerLastname, String managerImage, String managerPhone, String managerOffice, String managerLineid, String managerFacebook) {
        this.managerUsername = managerUsername;
        this.managerPassword = managerPassword;
        this.managerFirstname = managerFirstname;
        this.managerLastname = managerLastname;
        this.managerImage = managerImage;
        this.managerPhone = managerPhone;
        this.managerOffice = managerOffice;
        this.managerLineid = managerLineid;
        this.managerFacebook = managerFacebook;
    }

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
}
