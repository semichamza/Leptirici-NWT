package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by glasshark on 04-Apr-15.
 */
@Entity
@Table (name = "projects_users")
@IdClass (ProjectUserId.class)
public class ProjectUser implements Serializable
{
    private Integer userId;
    private Integer projectId;

    private User user;
    private Project project;

    private ProjectRole projectRole;

    @Id
    @Column (name = "user_id")
    @JsonIgnore
    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    @Id
    @Column (name = "project_id")
    @JsonIgnore
    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId = projectId;
    }

    @ManyToOne
    @JoinColumn (name = "project_id", updatable = false, insertable = false, referencedColumnName = "id")
    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn (name = "user_id", updatable = false, insertable = false, referencedColumnName = "id")
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @OneToOne
    @JoinColumn (name = "project_role_id")
    public ProjectRole getProjectRole()
    {
        return projectRole;
    }

    public void setProjectRole(ProjectRole projectRole)
    {
        this.projectRole = projectRole;
    }
}
