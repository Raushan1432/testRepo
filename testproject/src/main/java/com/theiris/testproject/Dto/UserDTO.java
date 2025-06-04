package com.theiris.testproject.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String id;
    private String username;
    private String email;
    private String phone;
    private String password;
}
