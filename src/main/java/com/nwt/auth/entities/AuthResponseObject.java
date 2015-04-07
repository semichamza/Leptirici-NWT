package com.nwt.auth.entities;

import java.io.Serializable;

/**
 * Created by Jasmin on 04-Apr-15.
 */
public class AuthResponseObject implements Serializable {
    String jwt;
    String message;
    Boolean isAutorized = false;


    public AuthResponseObject() {

    }

    public AuthResponseObject(String jwt, String message, Boolean isAutorized) {
        this.jwt = jwt;
        this.message = message;
        this.isAutorized = isAutorized;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsAutorized() {
        return isAutorized;
    }

    public void setIsAutorized(Boolean isAutorized) {
        this.isAutorized = isAutorized;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
