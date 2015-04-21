package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.util.CollectionUtil;
import com.nwt.util.EntityExtractor;

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
    private List<ProjectUser> projectUsers;
    private List<Task> tasks;

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

    @OneToMany (mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<ProjectUser> getProjectUsers()
    {
        return projectUsers;
    }

    public void setProjectUsers(List<ProjectUser> users)
    {
        this.projectUsers = users;
    }

    @JsonIgnore
    @Transient
    public Users getUsers()
    {
        Users users = new Users(CollectionUtil.extract(projectUsers, new EntityExtractor<User, ProjectUser>()
        {
            @Override
            public User extract(ProjectUser projectUser)
            {
                return projectUser.getUser();
            }
        }));
        return users;
    }

    @OneToMany (mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public String toString()
    {
        return "project name: " + name;
    }
}
