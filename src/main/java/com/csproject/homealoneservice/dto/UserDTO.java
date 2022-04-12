package com.csproject.homealoneservice.dto;

public class UserDTO {
    private int Id;

    private String Username;

    private String Password;

    private Integer Status;

    public UserDTO(String username, String password) {
        Username = username;
        Password = password;
    }

    public UserDTO() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
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
