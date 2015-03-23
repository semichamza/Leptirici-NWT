package com.nwt.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by glasshark on 18-Mar-15.
 */
@Entity
@Table (name = "Users")
@NamedQuery (name = User.FIND_ALL, query = "SELECT u FROM Users u")
public class User implements Serializable
{
    public static final String FIND_ALL = "User.findAll";

    private Integer id;
    //TODO: razdvojiti username i password u Credentials @Embeddable entity
    private String username;
    private String password;
    private String passwordHash;
    private Boolean active;
    private UserType userType;
    private List<Comment> comments;
    private List<Project> projects;
    private List<Task> tasks;

    public User()
    {
    }

    public User(String username, String password, Boolean active)
    {
        this.username = username;
        setPassword(password);
        this.active = active;
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

    @Column (nullable = false, unique = true, length = 30)
    @NotNull
    @Size (max = 30)
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Transient
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        setPasswordHash(password);
    }

    @Column (nullable = false)
    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String password)
    {
//        @TODO generisati hash
        this.passwordHash = password;
    }

    @Column (nullable = false)
    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    @Enumerated (EnumType.STRING)
    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    @OneToMany (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    @OneToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "User_Project",
            joinColumns = @JoinColumn (name = "user_id"),
            inverseJoinColumns = @JoinColumn (name = "project_id"))
    public List<Project> getProjects()
    {
        return projects;
    }

    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }

    @OneToMany (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }
}
