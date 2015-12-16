package com.nwt.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by glasshark on 20-Mar-15.
 */
@Entity
@Table (name = "properties")
@NamedQueries ({
        @NamedQuery (name = ConfigProperty.FIND_ALL, query = "SELECT p FROM ConfigProperty p order by p.propertyOrder"),
        @NamedQuery (name = ConfigProperty.BY_ID, query = "SELECT p FROM ConfigProperty p WHERE p.id = :id"),
        @NamedQuery (name = ConfigProperty.FIND_BY_TEXT, query = "SELECT p FROM ConfigProperty p WHERE CONCAT(p.name,' ',p.value) like :text order by p.propertyOrder")
})
@JsonIdentityInfo (property = "id", generator = ObjectIdGenerators.PropertyGenerator.class, scope = ConfigProperty.class)
public class ConfigProperty implements Serializable
{
    public static final String FIND_ALL = "ConfigProperty.findAll";
    public static final String BY_ID = "ConfigProperty.byID";
    public static final String FIND_BY_TEXT = "ConfigProperty.byTeks";
    private String id;
    private String value;
    private String name;
    private Integer propertyOrder;

    public ConfigProperty()
    {
    }

    public ConfigProperty(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Id
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Column(nullable = false)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(unique = true)
    public Integer getPropertyOrder() {
        return propertyOrder;
    }

    public void setPropertyOrder(Integer propertyOrder) {
        this.propertyOrder = propertyOrder;
    }
}
