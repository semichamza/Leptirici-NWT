package com.nwt.rest;

import com.nwt.entities.User;
import com.nwt.entities.Users;
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

/**
 * Created by glasshark on 23-Mar-15.
 */
@Path ("users")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class UserRestService
{
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;

    @GET
    public Response getAllUsers()
    {
        Users users = entityFacade.getAllUsers();
        logger.debug("getAllUsers() returned " + users.size() + " object(s).");
        return Response.ok(users).build();
    }

    @GET
    @Path ("{id}")
    public Response getUserById(@PathParam ("id") Integer id)
    {
        User user = entityFacade.getUserById(id);
        if (user == null)
            throw new NotFoundException();
        logger.debug("getUserById() returned: " + user.toString());
        return Response.ok(user).build();
    }

    @GET
    @Path ("{username: [a-zA-Z]*}")
    public Response getUserByUsername(@PathParam ("username") String username)
    {
        User user = entityFacade.getUserByUsername(username);
        if (user == null)
            throw new NotFoundException();
        logger.debug("getUserByUsername() returned: " + user.toString());
        return Response.ok(user).build();
    }

    @POST
    public Response createUser(User user)
    {
        if (user == null)
            throw new BadRequestException();
        user = entityFacade.createUser(user);
        logger.debug("Created user - " + user.toString());
        URI userUri = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();
        return Response.created(userUri).build();
    }

    @PUT
    public Response updateUser(User user)
    {
        User updatingUser = entityFacade.getUserById(user.getId());
        if (updatingUser == null)
            throw new BadRequestException();//TODO: Implementirat validatore!
        entityFacade.updateUser(updatingUser);
        logger.debug("Updated user  - " + updatingUser.toString());
        return Response.ok(updatingUser).build();
    }

    @DELETE
    @Path ("{id}")
    public Response deleteUser(@PathParam ("id") Integer id)
    {
        User user = entityFacade.getUserById(id);
        if (user == null)
            throw new NotFoundException();
        entityFacade.deleteUser(user);
        logger.debug("Deleted user - " + user.toString());
        return Response.noContent().build();
    }

    @GET
    @Path ("search/{text}")
    public Response searchUsers(@PathParam ("text") String text)
    {
        return Response.ok(entityFacade.searchUsers(text)).build();
    }
}
