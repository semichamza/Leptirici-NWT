//package com.nwt.entities;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
///**
// * Created by glasshark on 20-Mar-15.
// */
//@Entity
//@Table (name = "project_roles")
//@NamedQueries ({
//        @NamedQuery (name = ProjectRole.FIND_ALL, query = "SELECT pr FROM ProjectRole pr"),
//})
//@JsonIdentityInfo (generator = ObjectIdGenerators.None.class, scope = ProjectRole.class)
//public class ProjectRole implements Serializable
//{
//    public static final String FIND_ALL = "ProjectRole.findAll";
//    private Integer id;
//    private String name;
//
//    @Id
//    @GeneratedValue
//    public Integer getId()
//    {
//        return id;
//    }
//
//    public void setId(Integer id)
//    {
//        this.id = id;
//    }
//
//    @Column (nullable = false, length = 30)
//    public String getName()
//    {
//        return name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }
//}