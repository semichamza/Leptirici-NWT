package com.nwt.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
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

    @Column (nullable = false, columnDefinition = "TEXT")//MySQL ili length 2000
    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    @Temporal (TemporalType.TIMESTAMP)
    public Date getTimePosted()
    {
        return timePosted;
    }

    public void setTimePosted(Date timePosted)
    {
        this.timePosted = timePosted;
    }
}
