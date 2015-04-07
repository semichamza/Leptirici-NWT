package com.nwt.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by glasshark on 04-Apr-15.
 */
@Embeddable
public class UserPrincipal implements Serializable
{
    private String username;
    private String passwordHash;
    private UserRole userRole;

    @NotNull
    @Size (max = 30)
    @Column (nullable = false, unique = true, length = 30)
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @NotNull
    @Size (max = 32)
    @Column (nullable = false, length = 32)
    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    @OneToOne
    @JoinColumn (name = "user_role_id")
    public UserRole getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRole userRole)
    {
        this.userRole = userRole;
    }
}
