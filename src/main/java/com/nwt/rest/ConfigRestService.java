package com.nwt.rest;

import com.nwt.entities.*;
import com.nwt.facade.EntityFacade;
import com.nwt.util.Log;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collections;
import java.util.Date;

/**
 * Created by glasshark on 23-Mar-15.
 */
@Path ("config")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
@Stateless
public class ConfigRestService
{
    @Context
    protected UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;

    @GET
    public Response getConfigs()
    {
       ConfigProperties configProperties=entityFacade.getConfigProperties();
        logger.debug("getConfigProperties() returned " + configProperties.size() + " object(s).");
        return Response.ok(configProperties).build();
    }

    @PUT
    public Response updateProperties(ConfigProperties properties)
    {
        properties=entityFacade.updateProperties(properties);
        return Response.ok(properties).build();
    }

    @GET
    @Path("/search/{text}")
    public Response searchConfigs(@PathParam ("text") String text)
    {
        ConfigProperties properties = entityFacade.searchConfigs(text);
        logger.debug("getAllUsers() returned " + properties.size() + " object(s).");
        return Response.ok(properties).build();
    }
}
