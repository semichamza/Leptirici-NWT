package com.nwt.util;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by glasshark on 25-Mar-15.
 */
@ApplicationPath("auth")
public class NWTApplicationAuth extends ResourceConfig {
    public NWTApplicationAuth() {
        packages("com.nwt.auth");
    }
}
