package com.nwt.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "Projects")
@NamedQuery (name = Project.FIND_ALL, query = "SELECT p FROM Projects p")
public class Project implements Serializable
{
    public static final String FIND_ALL = "Project.findAll";

    private Integer id;
    private String name;
    private String description;
    //    private User owner;
//    private List<User> members;
    private List<Task> tasks;

    public Project()
    {
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

    @Column (nullable = false, length = 50)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

//    public User getOwner()
//    {
//        return owner;
//    }
//
//    public void setOwner(User owner)
//    {
//        this.owner = owner;
//    }

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "USER_PROJECT",
//            joinColumns= @JoinColumn(name = "PROJECT_ID"),
//            inverseJoinColumns= @JoinColumn(name = "USER_ID") )
//    public List<User> getMembers()
//    {
//        return members;
//    }
//
//    public void setMembers(List<User> members)
//    {
//        this.members = members;
//    }

    @OneToMany
    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }
}
