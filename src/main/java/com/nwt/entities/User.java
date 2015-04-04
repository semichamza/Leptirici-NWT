package com.nwt.entities;

import com.nwt.util.LifeCycleListener;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by glasshark on 18-Mar-15.
 */
@Entity
@Table (name = "users")
//@XmlRootElement
@NamedQueries ({
        @NamedQuery (name = User.FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery (name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username")
})
@EntityListeners (LifeCycleListener.class)
public class User implements Serializable
{
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_USERNAME = "User.findByUsername";

    private Integer id;
    //TODO: razdvojiti username i password u Credentials @Embeddable entity
    private String username;
    //    private String password;
    private String passwordHash;
    private Boolean active;
//    private UserType userType;

    public User()
    {
    }

    public User(String username, String password, Boolean active)
    {
        this.username = username;
        this.passwordHash = DigestUtils.md5Hex(password);
        ;
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

//    @Transient
//    public String getPassword()
//    {
//        return password;
//    }
//
//    public void setPassword(String password)
//    {
//        setPasswordHash(password);
//    }

    @Column (nullable = false, length = 32)
    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
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

//    @Enumerated (EnumType.STRING)
////    @Column (nullable = false)
//    public UserType getUserType()
//    {
//        return userType;
//    }
//
//    public void setUserType(UserType userType)
//    {
//        this.userType = userType;
//    }

    @PostLoad
    public void log()
    {
        System.out.print("pokrenuto");
    }

    @PrePersist
    @PreUpdate
    private void validate()
    {
        if (username == null)
            throw new IllegalArgumentException("Invalid first name");
        //TODO ovo se moze uradi odlicno za TIme metodu za logove i estimaciju da se provjeri unesena vrijednsto
        // i odmah unese u bazu u korektnom formatu ili cak se odvojit u posebnu listener klasu sa anotacijama
    }

    @Override
    public String toString()
    {
        return "username: " + username;
    }
}
