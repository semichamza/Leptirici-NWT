package com.nwt.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "projects")
@NamedQuery (name = Project.FIND_ALL, query = "SELECT p FROM Project p")
public class Project implements Serializable
{
    public static final String FIND_ALL = "Project.findAll";

    private Integer id;
    private String name;
    private String description;
    private User owner;
    private List<User> members;

    public Project()
    {
    }

    public Project(String name, String description, User owner)
    {
        this.name = name;
        this.description = description;
        this.owner = owner;
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

    @OneToOne
    @JoinColumn (name = "owner_id")
    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User owner)
    {
        this.owner = owner;
    }

    //TODO: dodat primary key u tabelu
    @OneToMany (fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn (name = "user_id"))
    public List<User> getMembers()
    {
        return members;
    }

    public void setMembers(List<User> members)
    {
        this.members = members;
    }

    @Override
    public String toString()
    {
        return "project name: " + name;
    }
}
