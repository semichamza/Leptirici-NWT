package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.nwt.enums.TaskPriorityEnum;
import com.nwt.enums.TaskStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "tasks")
@NamedQueries ({
        @NamedQuery (name = Task.FIND_ALL, query = "SELECT t FROM Task t"),
        @NamedQuery (name = Task.USER_TASK, query = "SELECT t FROM Task t where t.user.id=:userId AND t.project.id=:projectId"),
        @NamedQuery (name = Task.PROJECT_TASKS, query = "SELECT t FROM Task t where t.project.id=:project_id")
})
@JsonIdentityInfo (generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Task.class)
public class Task implements Serializable
{
    public static final String FIND_ALL = "Task.findAll";
    public static final String PROJECT_TASKS = "Task.projectTasks";
    public static final String USER_TASK = "Task.userTasks";

    private Integer id;
    private Date timeCreated;
    private String name;
    private String description;
    private User user;
    private Project project;
    private TaskStatusEnum taskStatus;
    private TaskPriorityEnum taskPriority;
    private String dueDate;
    private List<Log> logs;
    private List<Comment> comments;

    public Task()
    {
    }

    public Task(Date timeCreated, String name)
    {
        this.timeCreated = timeCreated;
        this.name = name;
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

    @Column (name = "time_created")
    @Temporal (TemporalType.TIMESTAMP)
    public Date getTimeCreated()
    {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated)
    {
        this.timeCreated = timeCreated;
    }

    @Column (nullable = false, length = 100)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column (length = 1000)
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    @ManyToOne
    public Project getProject()
    {
        return project;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    //    @Temporal(TemporalType.TIME)

    @Enumerated (EnumType.STRING)
    public TaskStatusEnum getTaskStatus()
    {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusEnum taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    @Enumerated (EnumType.STRING)
    public TaskPriorityEnum getTaskPriority()
    {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriorityEnum taskPriority)
    {
        this.taskPriority = taskPriority;
    }

    @JsonIgnore
    @OneToMany (mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Log> getLogs()
    {
        return logs;
    }

    public void setLogs(List<Log> logs)
    {
        this.logs = logs;
    }

    @OneToMany (mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy ("timePosted DESC")
    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.   comments = comments;
    }

    @Override
    public String toString()
    {
        return "task: " + name + "(id: " + id + ")";
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Date dueDateObject()
    {
        Date date=null;
        try
        {
            date= new SimpleDateFormat("dd-DD-yyyy").parse(getDueDate().substring(0,10));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }


    public Integer priorityLevel()
    {
        if(getTaskPriority()==null)
            return -1;
        switch (getTaskPriority())
        {
            case HIGH:
                return 10;
            case NORMAL:
                return 5;
            case LOW:
                return 0;
        }
        return -1;
    }
}
