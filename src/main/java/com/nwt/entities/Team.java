package com.nwt.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
public class Team implements Serializable
{
    private Integer id;

    @Id
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
