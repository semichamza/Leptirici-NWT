package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.util.CollectionUtil;
import com.nwt.util.EntityExtractor;
import com.nwt.util.LifeCycleListener;
import com.nwt.util.jsog.JSOGGenerator;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by glasshark on 18-Mar-15.
 */
@Entity
@Table (name = "users")
@NamedQueries ({
        @NamedQuery (name = User.FIND_ALL, query = "SELECT u FROM User u WHERE u.deleted=:deleted"),
        @NamedQuery (name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.userPrincipal.username = :username"),
        @NamedQuery (name = User.FIND_BY_PROJECT_ID, query = "SELECT u FROM User u,ProjectUser up WHERE u.id=up.userId and up.projectId=:projectId"),
        @NamedQuery (name = User.UNLONKED_USERS, query = "SELECT u FROM User u WHERE u.id NOT IN(SELECT up.userId FROM ProjectUser up where up.projectId=:projectId)"),
        @NamedQuery (name = User.DELETE_FROM_PROJECT, query = "DELETE FROM ProjectUser where projectId=:projectId AND userId=:userId"),
        @NamedQuery (name = User.FIND_BY_TEXT, query = "SELECT u FROM User u WHERE CONCAT(u.firstName,' ' , u.lastName ,' ', u.userPrincipal.username) like :text and u.deleted=:deleted")
})
@EntityListeners (LifeCycleListener.class)
@JsonIdentityInfo (generator = JSOGGenerator.class)
public class User implements Serializable
{
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_TEXT = "User.findByText";
    public static final String FIND_BY_PROJECT_ID = "User.finByProjectId";
    public static final String UNLONKED_USERS = "User.unlinked";
    public static final String DELETE_FROM_PROJECT = "User.deleteFromProject";

    private Integer id;
    private UserPrincipal userPrincipal;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Boolean blocked;
    private Boolean deleted;
    private String email;
    private List<ProjectUser> projectUsers;
    private List<Task> tasks;
    private List<Comment> comments;
    private List<Log> logs;

    public User()
    {
    }

    public User(User user)
    {
        this(user.getUserPrincipal(),user.getFirstName(),user.getLastName(),user.getActive(),user.getEmail());
        setId(user.id);
    }
    public User(UserPrincipal userPrincipal, String firstName, String lastName, Boolean active, String email)
    {
        this.userPrincipal = userPrincipal;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.email = email;
    }

//    TODO
//    @XmlJavaTypeAdapter (LinkAdapter.class)
//    @InjectLink (value = "users/{id}", rel = "self", style = InjectLink.Style.ABSOLUTE)
//    private Link link;

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

    @Embedded
    public UserPrincipal getUserPrincipal()
    {
        return userPrincipal;
    }

    public void setUserPrincipal(UserPrincipal userPrincipal)
    {
        this.userPrincipal = userPrincipal;
    }

    @NotNull
    @Size (max = 30)
    @Column (nullable = false, length = 30)
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @NotNull
    @Size (max = 50)
    @Column (nullable = false, length = 50)
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Email
    @Pattern (regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
            + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    @Size (max = 50)
    @Column (unique = true, length = 50)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @NotNull
    @Column (nullable = false)
    public Boolean getActive()
    {
        return active;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<ProjectUser> getProjectUsers()
    {
        return projectUsers;
    }

    public void setProjectUsers(List<ProjectUser> projects)
    {
        this.projectUsers = projects;
    }

    @JsonIgnore
    @Transient
    public Projects getProjects()
    {
        Projects projects = new Projects(
                CollectionUtil.extract(projectUsers, new EntityExtractor<Project, ProjectUser>()
                {
                    @Override
                    public Project extract(ProjectUser projectUser)
                    {
                        return projectUser.getProject();
                    }
                }));
        return projects;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Task> getTasks()
    {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<Log> getLogs()
    {
        return logs;
    }

    public void setLogs(List<Log> logs)
    {
        this.logs = logs;
    }

    @Override
    public String toString()
    {
        return "username: " + userPrincipal.getUsername();
    }

    @Column
    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Column()
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
