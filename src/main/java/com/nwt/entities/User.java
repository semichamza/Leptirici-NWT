package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.util.LifeCycleListener;
import com.nwt.util.LinkAdapter;
import org.glassfish.jersey.linking.InjectLink;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by glasshark on 18-Mar-15.
 */
@Entity
@Table (name = "users")
@NamedQueries ({
        @NamedQuery (name = User.FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery (name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.userPrincipal.username = :username")
})
@EntityListeners (LifeCycleListener.class)
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable
{
    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_USERNAME = "User.findByUsername";

    private Integer id;
    private UserPrincipal userPrincipal;
    private String firstName;
    private String lastName;
    private Boolean active;
    private String email;
    private List<ProjectUser> projects;

    //TODO
    @XmlJavaTypeAdapter (LinkAdapter.class)
    @InjectLink (value = "users/{id}", rel = "self", style = InjectLink.Style.ABSOLUTE)
    private Link link;

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

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<ProjectUser> getProjects()
    {
        return projects;
    }

    public void setProjects(List<ProjectUser> projects)
    {
        this.projects = projects;
    }

    @Override
    public String toString()
    {
        return "username: " + userPrincipal.getUsername();
    }
}
