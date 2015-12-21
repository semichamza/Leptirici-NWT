package com.nwt.rest;

import com.nwt.entities.*;
import com.nwt.facade.EntityFacade;
import com.nwt.util.Log;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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
    protected UriInfo uriInfo;
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
    @Path("/deleted")
    public Response getAllDeletedUsers()
    {
        Users users = entityFacade.getAllDeletedUsers();
        logger.debug("getAllUsers() returned " + users.size() + " object(s).");
        return Response.ok(users).build();
    }

    //TODO bolje rijesiti exception handling
    private User userById(Integer id)
    {
        User user = entityFacade.getUserById(id);
        if (user == null)
            throw new NotFoundException("User not found");
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
    @Path ("/{username: [a-zA-Z1-9]*}")
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
        User orginUser=userById(user.getId());     //TODO: bolje implementirat validatore!
        orginUser.setFirstName(user.getFirstName());
        orginUser.setLastName(user.getLastName());
        orginUser.setEmail(user.getEmail());
        orginUser = entityFacade.updateUser(orginUser);
        logger.debug("Updated user  - " + orginUser.toString());
        return Response.ok(orginUser).build();
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

//    TODO: Vidjet koje je njabolje rjesenje kad se koriste transakcijske operacije, tj. kad vise objekata treba
    //TODO: sinhronizovat u isto vrijme
//    @POST
//    public Response createUserRole(UserRole userRole)
//    {
//        User user = userById(id);
//        entityFacade.deleteUser(user);
//        logger.debug("Deleted user - " + user.toString());
//        return Response.noContent().build();
//    }

    //    TODO: Dodat vise parametara pretrage
    @GET
    @Path("/search/{text}/{deleted}")
    public Response searchUsers(@PathParam ("text") String text,@PathParam ("deleted") String deleted)
    {
        boolean isDeleted=deleted.equals("deleted");
        Users users = entityFacade.searchUsers(text,isDeleted);
        logger.debug("getAllUsers() returned " + users.size() + " object(s).");
        return Response.ok(users).build();
    }

    @GET
    @Path ("/{userId}/projects")
    public Response getAllUserProjects(@PathParam ("userId") Integer userId)
    {
        User user = userById(userId);
        Projects projects = user.getProjects();
        Projects tmpProjects=new Projects();
        Projects tmpProjectsClosed=new Projects();
        for(Project project:projects)
        {
            if(project.getClosed()!=null)
            {
                tmpProjectsClosed.add(project);
            }
            else {
                tmpProjects.add(project);
            }
        }

        projects.clear();
        projects.addAll(tmpProjects);
        projects.addAll(tmpProjectsClosed);
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

    @PUT
    @Path ("/block/{id}")
    public Response blockUser(@PathParam ("id") Integer userId)
    {
        User user=entityFacade.getUserById(userId);
        user.setBlocked(true);
        entityFacade.updateUser(user);
        return Response.ok().build();
    }

    @PUT
    @Path ("/block")
    public Response blockUsers(Users users)
    {
       for(User tempUser:users.getUsers())
       {
           User user=entityFacade.getUserById(Integer.valueOf(tempUser.getId()));
           user.setBlocked(true);
           entityFacade.updateUser(user);
       }
        return Response.ok().build();
    }

    @PUT
    @Path ("unblock")
    public Response unblockUsers(Users users)
    {
        for(User tempUser:users.getUsers())
        {
            User user=entityFacade.getUserById(Integer.valueOf(tempUser.getId()));
            user.setBlocked(false);
            entityFacade.updateUser(user);
        }
        return Response.ok().build();
    }

    @PUT
    @Path ("delete")
    public Response deleteUsers(Users users)
    {
        for(User tempUser:users.getUsers())
        {
            User user=entityFacade.getUserById(Integer.valueOf(tempUser.getId()));
            user.setDeleted(true);
            entityFacade.updateUser(user);
        }
        return Response.ok().build();
    }

    @PUT
    @Path ("revert")
    public Response revertUsers(Users users)
    {
        for(User tempUser:users.getUsers())
        {
            User user=entityFacade.getUserById(Integer.valueOf(tempUser.getId()));
            user.setDeleted(false);
            entityFacade.updateUser(user);
        }
        return Response.ok().build();
    }

    @PUT
    @Path ("/unblock/{id}")
    public Response unblockUser(@PathParam ("id") Integer userId)
    {
        User user=entityFacade.getUserById(userId);
        user.setBlocked(false);
        entityFacade.updateUser(user);
        return Response.ok().build();
    }

    @POST
    @Path ("/messages/send")
    public Response sendMessage(Message message)
    {
        message.setId(null);
        message.setSeen(false);
        message.setDate(new Date());
        message=entityFacade.createMessage(message);
        return Response.ok(message).build();
    }

    @GET
    @Path ("/{id}/messages")
    public Response getMessages(@PathParam("id")Integer userId)
    {
        Messages messages=entityFacade.getUserMessage(userId);
        Collections.reverse(messages);
        return Response.ok(messages).build();
    }

    @GET
    @Path ("/{id}/sentmessages")
    public Response getSentMessages(@PathParam("id")Integer userId)
    {
        Messages messages=entityFacade.getUserSentMessage(userId);
        Collections.reverse(messages);
        return Response.ok(messages).build();
    }
    @GET
    @Path ("/{id}/messages/unread")
    public Response getUnreadMesages(@PathParam("id")Integer userId)
    {
        Messages messages=entityFacade.getUnreadMessages(userId);
        Collections.reverse(messages);
        return Response.ok(messages).build();
    }

    @PUT
    @Path ("/{id}/messages/readAll")
    public Response readAllMessages(@PathParam("id")Integer userId)
    {
        Messages messages=entityFacade.getUnreadMessages(userId);
        for(Message message:messages)
        {
            message.setSeen(true);
            entityFacade.updateMessage(message);
        }
        return Response.ok().build();
    }
}
