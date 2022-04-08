package com.csproject.homealoneservice.dto;

public class UserDTO {

    private String Username;

    private String Password;

    private int Status;

    public UserDTO(String username, String password) {
        Username = username;
        Password = password;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int managerStatus) {
        this.Status = managerStatus;
    }
}
