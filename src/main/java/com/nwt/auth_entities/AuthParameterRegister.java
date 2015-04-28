package com.nwt.auth_entities;

import java.io.Serializable;

/**
 * Created by Jasmin on 04-Apr-15.
 */
public class AuthParameterRegister implements Serializable {
    String username;
    String password;
    String email;

    String firstName;
    String lastName;

    public AuthParameterRegister() {
    }

    public AuthParameterRegister(String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }
}
