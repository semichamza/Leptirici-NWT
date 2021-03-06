package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "comments")
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Comment.class)
public class Comment implements Serializable
{
    private Integer id;
    private String text;
    private Task task;
    private User user;
    private Date timePosted;

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

    @Column (nullable = false, columnDefinition = "TEXT")//samo za MySQL (ili length 2000)
    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Temporal (TemporalType.TIMESTAMP)
    @Column (name = "time_posted", nullable = false)
    public Date getTimePosted()
    {
        return timePosted;
    }

    public void setTimePosted(Date timePosted)
    {
        this.timePosted = timePosted;
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
