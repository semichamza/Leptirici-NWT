package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.enums.ProjectRoleEnum;
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
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Project.class)
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

    @JsonIgnore
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

    @JsonIgnore
    @OneToMany (mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    @JsonIgnore
    public void addUser(User user, ProjectRoleEnum projectRole)
    {
        ProjectUser projectUser = new ProjectUser(user, this, projectRole);
        projectUsers.add(projectUser);
    }

    @JsonIgnore
   public ProjectUser getProjectUser(Integer id)
    {
        for(ProjectUser projectUser:projectUsers)
        {
            if(projectUser.getUserId().equals(id))
                return projectUser;
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "project name: " + name;
    }
}
