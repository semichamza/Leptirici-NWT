package com.nwt.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "project_roles")
public class ProjectRole implements Serializable
{
    private Integer id;
    private String name;

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

    @Column (nullable = false, length = 30)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}