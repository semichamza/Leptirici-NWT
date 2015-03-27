package com.nwt.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by glasshark on 25-Mar-15.
 */
@ApplicationPath ("/rest")
public class NWTApplication extends ResourceConfig
{
    public NWTApplication()
    {
        packages("com.nwt.rest");
    }
}
