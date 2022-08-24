package com.laurentiuspilca.ssia.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Otp {

    @Id
    private String username;
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
