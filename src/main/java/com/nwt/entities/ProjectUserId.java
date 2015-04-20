package com.nwt.entities;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

/**
 * Created by glasshark on 04-Apr-15.
 */
public class ProjectUserId implements Serializable
{
    private Integer userId;
    private Integer projectId;

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Integer projectId)
    {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectUserId that = (ProjectUserId)o;

        if (!userId.equals(that.userId)) return false;
        return projectId.equals(that.projectId);

    }

    @Override
    public int hashCode()
    {
        int result = userId.hashCode();
        result = 31 * result + projectId.hashCode();
        return result;
    }
}
