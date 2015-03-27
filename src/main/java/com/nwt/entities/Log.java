package com.nwt.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "logs")
public class Log implements Serializable
{
    private Integer id;
    private Calendar time;
    private Comment comment;
    private Task task;
//    private User user;

    public Log()
    {
    }

    public Log(Calendar time, Task task, User user)
    {
        this.time = time;
        this.task = task;
//        this.user = user;
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

    @OneToOne
    public Comment getComment()
    {
        return comment;
    }

    public void setComment(Comment comment)
    {
//        comment.setTask(task);
//        comment.setUser(user);
        this.comment = comment;
    }

    @OneToOne
    public Task getTask()
    {
        return task;
    }

    public void setTask(Task task)
    {
        this.task = task;
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
}
