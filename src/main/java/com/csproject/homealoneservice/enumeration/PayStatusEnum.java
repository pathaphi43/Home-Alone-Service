package com.csproject.homealoneservice.enumeration;

public enum PayStatusEnum {
    Success_Status(2),
    First_Status(0),
    Prepare_Status(1)
            ;

    PayStatusEnum(int status) {
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
