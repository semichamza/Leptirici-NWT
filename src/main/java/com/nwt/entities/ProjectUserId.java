package com.nwt.entities;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by glasshark on 04-Apr-15.
 */
@Embeddable
public class ProjectUserId implements Serializable
{
    private Project project;
    private User user;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "project_id")
    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
