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

/**
 * Created by glasshark on 23-Mar-15.
 */
@Path ("users")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
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

    //TODO bolje rijesiti exception handling
    private User userById(Integer id)
    {
        User user = entityFacade.getUserById(id);
        if (user == null)
            throw new NotFoundException();
        return user;
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam ("id") Integer id)
    {
        User user = userById(id);
        logger.debug("getUserById(" + id + ") returned: " + user.toString());
        return Response.ok(user).build();
    }

    @GET
    @Path("/{username: [a-zA-Z]*}")
    public Response getUserByUsername(@PathParam ("username") String username)
    {
        User user = entityFacade.getUserByUsername(username);
        if (user == null)
            throw new NotFoundException();
        logger.debug("getUserByUsername(" + username + ") returned: " + user.toString());
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
        userById(user.getId());     //TODO: bolje implementirat validatore!
        entityFacade.updateUser(user);
        logger.debug("Updated user  - " + user.toString());
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam ("id") Integer id)
    {
        User user = userById(id);
        entityFacade.deleteUser(user);
        logger.debug("Deleted user - " + user.toString());
        return Response.noContent().build();
    }

    //    TODO: Dodat vise parametara pretrage
    @GET
    @Path("/search/{text}")
    public Response searchUsers(@PathParam ("text") String text)
    {
        return Response.ok(entityFacade.searchUsers(text)).build();
    }

    @GET
    @Path ("/{userId}/projects")
    public Response getAllUserProjects(@PathParam ("userId") Integer userId)
    {
        User user = userById(userId);
        Projects projects = user.getProjects();
        logger.debug("getAllUserProjects() returned " + projects.size() + " object(s).");
        return Response.ok(projects).build();
    }

    @GET
    @Path ("/{userId}/projects/{projectId}")
    public Response getUserProjectById(@PathParam ("userId") Integer userId, @PathParam ("projectId") Integer projectId)
    {
        Project project = entityFacade.getUserProjectById(userId, projectId);
        logger.debug("getUserProjectById(" + userId + ", " + projectId + ") returned: " + project.toString());
        return Response.ok(project).build();
    }

    @GET
    @Path ("/{userId}/tasks")
    public Response getAllUserTasks(@PathParam ("userId") Integer userId)
    {
        User user = userById(userId);
        Tasks tasks = new Tasks(user.getTasks());
        logger.debug("getAllUserTasks() returned " + tasks.size() + " object(s).");
        return Response.ok(tasks).build();
    }

    @GET
    @Path ("/{userId}/tasks/{taskId}")
    public Response getUserTaskById(@PathParam ("userId") Integer userId, @PathParam ("taskId") Integer taskId)
    {
        //TODO Implement!
        logger.debug("getUserTaskById() not implemented yet");
        return null;
    }

    @GET
    @Path ("/{userId}/comments")
    public Response getAllUserComments(@PathParam ("userId") Integer userId)
    {
        User user = userById(userId);
        Comments comments = new Comments(user.getComments());
        logger.debug("getAllUserComments() returned " + comments.size() + " object(s).");
        return Response.ok(comments).build();
    }

    @GET
    @Path ("/{userId}/comments/{commentId}")
    public Response getUserCommentById(@PathParam ("userId") Integer userId, @PathParam ("commentId") Integer commentId)
    {
        //TODO Implement!
        logger.debug("getAllUserComments() not implemented yet");
        return null;
    }
}
