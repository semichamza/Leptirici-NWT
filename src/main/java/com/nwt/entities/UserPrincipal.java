package com.nwt.entities;

import com.nwt.enums.UserRoleEnum;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
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
    private UserRoleEnum userRole;

    public UserPrincipal()
    {
    }

    public UserPrincipal(String username, String password, UserRoleEnum userRole)
    {
        this.username = username;
        this.passwordHash = DigestUtils.md5Hex(password);
        this.userRole = userRole;
    }

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

    @Enumerated (EnumType.STRING)
    public UserRoleEnum getUserRole()
    {
        return userRole;
    }

    public void setUserRole(UserRoleEnum userRole)
    {
        this.userRole = userRole;
    }

    @Transient
    public void setPassword(String password) {
        passwordHash = DigestUtils.md5Hex(password);
    }
}
