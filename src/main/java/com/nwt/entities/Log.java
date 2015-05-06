package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "logs")
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Log.class)
public class Log implements Serializable
{
    private Integer id;
    private Calendar time;
    private Comment comment;
    private Task task;
    private User user;

    public Log()
    {
    }

    public Log(Calendar time, Task task, User user)
    {
        this.time = time;
        this.task = task;
        this.user = user;
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

    @Column (nullable = false)
    public Calendar getTime()
    {
        return time;
    }

    public void setTime(Calendar time)
    {
        this.time = time;
    }

    @OneToOne (cascade = CascadeType.ALL)
    public Comment getComment()
    {
        return comment;
    }

    public void setComment(Comment comment)
    {
        this.comment = comment;
    }

    @ManyToOne
    public Task getTask()
    {
        return task;
    }

    public void setTask(Task task)
    {
        this.task = task;
    }

    @ManyToOne
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
