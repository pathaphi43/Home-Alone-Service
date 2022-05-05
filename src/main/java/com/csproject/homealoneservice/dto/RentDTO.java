package com.csproject.homealoneservice.dto;

public class RentDTO {

    private int hid;
    private int tid;

    public RentDTO() {
    }

    public RentDTO(int hid, int tid) {
        this.hid = hid;
        this.tid = tid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }
}
