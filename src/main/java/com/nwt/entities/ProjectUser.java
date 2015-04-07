package com.nwt.entities;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by glasshark on 04-Apr-15.
 */
@Entity
@Table (name = "projects_users")
@AssociationOverrides ({
        @AssociationOverride (name = "id.project",
                joinColumns = @JoinColumn (name = "project_id")),
        @AssociationOverride (name = "id.user",
                joinColumns = @JoinColumn (name = "user_id"))})
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectUser
{
    private ProjectUserId id;
    private ProjectRole projectRole;

    @EmbeddedId
    public ProjectUserId getId()
    {
        return id;
    }

    public void setId(ProjectUserId id)
    {
        this.id = id;
    }

    @Transient
    public Project getProject()
    {
        return id.getProject();
    }

    public void setProject(Project project)
    {
        id.setProject(project);
    }

    @Transient
    public User getUser()
    {
        return id.getUser();
    }

    public void setUser(User user)
    {
        id.setUser(user);
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
