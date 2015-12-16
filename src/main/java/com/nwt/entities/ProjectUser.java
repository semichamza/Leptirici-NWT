package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.enums.ProjectRoleEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by glasshark on 04-Apr-15.
 */
@Entity
@Table (name = "projects_users")
@IdClass (ProjectUserId.class)
@JsonIdentityInfo (generator = ObjectIdGenerators.None.class, scope = ProjectUser.class)
public class ProjectUser implements Serializable
{
    private Integer userId;
    private Integer projectId;
    private User user;
    private Project project;
    private ProjectRoleEnum projectRole;

    public ProjectUser()
    {
    }

    public ProjectUser(User user, Project project, ProjectRoleEnum projectRole)
    {
        this.user = user;
        this.userId = user.getId();
        this.project = project;
        this.projectId = project.getId();
        this.projectRole = projectRole;
    }

    @Id
    @Column (name = "user_id")
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

    @Enumerated (EnumType.STRING)
    public ProjectRoleEnum getProjectRole()
    {
        return projectRole;
    }

    public void setProjectRole(ProjectRoleEnum projectRole)
    {
        this.projectRole = projectRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectUser that = (ProjectUser) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return !(projectId != null ? !projectId.equals(that.projectId) : that.projectId != null);

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
    }
}
