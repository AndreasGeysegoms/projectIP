package com.example.planner.dto;

import javax.validation.constraints.NotEmpty;

public class CreateUserDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Username: " + this.username + "; Password: " + this.password;
    }
}
