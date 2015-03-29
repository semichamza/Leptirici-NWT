package com.nwt.util;

import com.nwt.rest.UserRestService;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by glasshark on 25-Mar-15.
 */
@ApplicationPath ("rest")
public class NWTApplication extends ResourceConfig
{
    public NWTApplication()
    {
        packages("com.nwt.rest");
    }
}
