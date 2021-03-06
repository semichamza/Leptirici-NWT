package com.nwt.auth.entities;

import com.nwt.entities.UserPrincipal;
import org.joda.time.DateTime;

public class Token {
    private String username;
    private DateTime issued;
    private DateTime expires;
    private String passwordHash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DateTime getIssued() {
        return issued;
    }

    public void setIssued(DateTime issued) {
        this.issued = issued;
    }

    public DateTime getExpires() {
        return expires;
    }

    public void setExpires(DateTime expires) {
        this.expires = expires;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isValid(UserPrincipal principal)
    {
        return passwordHash.equals(principal.getPasswordHash()) && username.equals(principal.getUsername());
    }


}