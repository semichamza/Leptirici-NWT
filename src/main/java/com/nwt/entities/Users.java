package com.nwt.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 28-Mar-15.
 */
@XmlRootElement
@XmlSeeAlso (User.class)
public class Users extends ArrayList<User>
{
    public Users()
    {
        super();
    }

    public Users(Collection<? extends User> c)
    {
        super(c);
    }

    @XmlElement (name = "user")
    public List<User> getUsers()
    {
        return this;
    }

    public void setUsers(List<User> users)
    {
        this.addAll(users);
    }
}
