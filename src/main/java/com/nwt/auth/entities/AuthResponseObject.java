package com.nwt.auth.entities;

import com.nwt.entities.User;

import java.io.Serializable;

/**
 * Created by Jasmin on 04-Apr-15.
 */
public class AuthResponseObject implements Serializable {
    String jwt;
/*    String message;
    Boolean isAutorized = false;
    String name;
    String id;
    String userRole;*/
    User user;
    public AuthResponseObject() {

    }

    /*public AuthResponseObject(String jwt, String message, Boolean isAutorized, String name) {
        this.jwt = jwt;
        this.message = message;
        this.isAutorized = isAutorized;
        this.name = name;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
*/
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
