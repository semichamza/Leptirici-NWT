package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "projects")
@NamedQuery (name = Project.FIND_ALL, query = "SELECT p FROM Project p")
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project implements Serializable
{
    public static final String FIND_ALL = "Project.findAll";

    private Integer id;
    private String name;
    private String description;
    private List<ProjectUser> users;

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

    @Column (nullable = false, length = 100)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column (length = 1000)
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @OneToMany (mappedBy = "id.project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<ProjectUser> getUsers()
    {
        return users;
    }

    public void setUsers(List<ProjectUser> users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "project name: " + name;
    }
}
