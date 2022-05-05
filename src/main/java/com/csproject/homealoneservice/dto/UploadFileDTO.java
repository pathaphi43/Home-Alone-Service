package com.csproject.homealoneservice.dto;

public class UploadFileDTO {

    private String imgPath;

    public UploadFileDTO() {

    }

    public UploadFileDTO(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
