package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "images")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Message.class)
public class UserImage implements Serializable
{
    private Integer id;
    private User user;
    private byte[] image;

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

    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Type(type="org.hibernate.type.BinaryType")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
