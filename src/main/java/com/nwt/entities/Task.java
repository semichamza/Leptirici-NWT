package com.nwt.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "tasks")
@NamedQuery (name = Task.FIND_ALL, query = "SELECT t FROM Task t")
public class Task implements Serializable
{
    public static final String FIND_ALL = "Task.findAll";

    private Integer id;
    private Date timeCreated;
    private String name;
    private String description;
    //    private User user;
    private Project project;
    private Calendar estimation;
    private Priority priority;
//    private List<Log> logs;
//    private List<Comment> comments;


    public Task()
    {
    }

    public Task(Date timeCreated, String name)
    {
        this.timeCreated = timeCreated;
        this.name = name;
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

    @Column (name = "time_created")
    @Temporal (TemporalType.TIMESTAMP)
    public Date getTimeCreated()
    {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated)
    {
        this.timeCreated = timeCreated;
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

//    @OneToOne
//    public User getUser()
//    {
//        return user;
//    }
//
//    public void setUser(User user)
//    {
//        this.user = user;
//    }

    @OneToOne
    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    //    @Temporal(TemporalType.TIME)
    public Calendar getEstimation()
    {
        return estimation;
    }

    public void setEstimation(Calendar estimation)
    {
        this.estimation = estimation;
    }

    @Enumerated (EnumType.STRING)
    public Priority getPriority()
    {
        return priority;
    }

    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }

//    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    public List<Log> getLogs()
//    {
//        return logs;
//    }
//
//    public void setLogs(List<Log> logs)
//    {
//        this.logs = logs;
//    }
//
//    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @OrderBy ("timePosted DESC")
//    public List<Comment> getComments()
//    {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments)
//    {
//        this.comments = comments;
//    }
}
