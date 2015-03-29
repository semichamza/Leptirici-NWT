package com.nwt.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by glasshark on 29-Mar-15.
 */
//@XmlRootElement
//@XmlSeeAlso (Project.class)
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

    //    @XmlElement (name = "project")
    public List<Project> getProjects()
    {
        return this;
    }

    public void setProjects(List<Project> projects)
    {
        this.addAll(projects);
    }
}