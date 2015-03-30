package com.nwt.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 29-Mar-15.
 */
public class Projects extends ArrayList<Project>
{
    public Projects()
    {
        super();
    }

    public Projects(Collection<? extends Project> c)
    {
        super(c);
    }

    public List<Project> getProjects()
    {
        return this;
    }

    public void setProjects(List<Project> projects)
    {
        this.addAll(projects);
    }
}