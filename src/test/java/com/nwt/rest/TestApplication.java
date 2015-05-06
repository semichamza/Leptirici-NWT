package com.nwt.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by glasshark on 26-Apr-15.
 */
@ApplicationPath ("/")
public class TestApplication extends Application
{

    // ======================================
    // =             Attributes             =
    // ======================================

    private final Set<Class<?>> classes;

    // ======================================
    // =            Constructors            =
    // ======================================

    public TestApplication()
    {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(UserRestServiceIT.class);
        classes = Collections.unmodifiableSet(c);
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    @Override
    public Set<Class<?>> getClasses()
    {
        return classes;
    }

}
