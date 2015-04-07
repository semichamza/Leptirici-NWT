package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 28-Mar-15.
 */
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

    public List<User> getUsers()
    {
        return this;
    }

    public void setUsers(List<User> users)
    {
        this.addAll(users);
    }
}
