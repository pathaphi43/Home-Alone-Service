package com.csproject.homealoneservice.enumeration;

public enum ManagerEnum {
    MANAGER_FIRST_INSERT("http://homealone.comsciproject.com/img/local_avatar.png",0),
    MANAGER_UPLOAD_PROFILE("http://homealone.comsciproject.com/img/",0)
    ;

    private String imagePath;
    private int status;

    ManagerEnum(String imagePath, int status) {
        this.imagePath = imagePath;
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
