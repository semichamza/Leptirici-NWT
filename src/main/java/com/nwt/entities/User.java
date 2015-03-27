package com.nwt.entities;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by glasshark on 18-Mar-15.
 */
@Entity
@Table (name = "users")
@NamedQuery (name = User.FIND_ALL, query = "SELECT u FROM User u")
public class User implements Serializable
{
    public static final String FIND_ALL = "User.findAll";

    private Integer id;
    //TODO: razdvojiti username i password u Credentials @Embeddable entity
    private String username;
    private String password;
    private String passwordHash;
    private Boolean active;
    private UserType userType;

    public User()
    {
    }

    public User(String username, String password, Boolean active)
    {
        this.username = username;
        setPassword(password);
        this.active = active;
    }

    @Id
    @GeneratedValue
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    //    @NotNull
//    @Size (max = 30)
    @Column (nullable = false, unique = true, length = 30)
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Transient
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        setPasswordHash(password);
    }

    @Column (nullable = false, length = 32)
    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String password)
    {
        this.passwordHash = DigestUtils.md5Hex(password);
    }

    @Column (nullable = false)
    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    @Enumerated (EnumType.STRING)
//    @Column (nullable = false)
    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }
}
