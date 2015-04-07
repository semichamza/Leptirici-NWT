package com.nwt.util;

import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by glasshark on 25-Mar-15.
 */
@ApplicationPath ("rest")
public class NWTApplication extends ResourceConfig
{
    public NWTApplication()
    {
        register(DeclarativeLinkingFeature.class);
        register(JacksonFeature.class);

        packages("com.nwt.entities", "com.nwt.rest", "com.nwt.util");
    }
}
