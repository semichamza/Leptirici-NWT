package com.nwt.auth.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.nwt.util.jsog.JSOGGenerator;

import java.io.Serializable;

/**
 * Created by jkaldzij on 29.01.2016..
 */

public class Password implements Serializable
{
    String userId;
    String password;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
