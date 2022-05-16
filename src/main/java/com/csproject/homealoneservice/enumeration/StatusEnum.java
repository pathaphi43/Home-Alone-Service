package com.csproject.homealoneservice.enumeration;

public enum StatusEnum {
    Cancel_Status(2),
    Prepare_Status(0),
    Normal_Status(1)
    ;

    StatusEnum(int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
