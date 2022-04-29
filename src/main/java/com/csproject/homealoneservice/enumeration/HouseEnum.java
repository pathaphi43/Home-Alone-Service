package com.csproject.homealoneservice.enumeration;

public enum HouseEnum {
    HOUSE_FIRST_INSERT("http://homealone.comsciproject.com/img/home.jpg",0);

    private String imagePath;
    private int status;

    HouseEnum(String imagePath, int status) {
        this.imagePath = imagePath;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
