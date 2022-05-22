package com.csproject.homealoneservice.dto;

public class UploadMultipartFileDTO {

    private String[] imgPath;

    public UploadMultipartFileDTO() {
    }

    public UploadMultipartFileDTO(String[] imgPath) {
        this.imgPath = imgPath;
    }

    public String[] getImgPath() {
        return imgPath;
    }

    public void setImgPath(String[] imgPath) {
        this.imgPath = imgPath;
    }
}
