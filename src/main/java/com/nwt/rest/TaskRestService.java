package com.nwt.rest;

import com.nwt.entities.Task;
import com.nwt.entities.Tasks;
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
@Path("tasks")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class TaskRestService {
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;

    @GET
    public Response getAllTasks() {
        Tasks tasks = entityFacade.getAllTasks();
        logger.debug("getAllTasks() returned " + tasks.size() + " object(s).");
        return Response.ok(tasks).build();
    }

    @GET
    @Path("{id}")
    public Response getTaskById(@PathParam("id") Integer id) {
        Task task = entityFacade.getTaskById(id);
        if (task == null)
            throw new NotFoundException();
        logger.debug("getTaskById() returned: " + task.toString());
        return Response.ok(task).build();
    }

    @POST
    public Response createTask(Task task) {
        if (task == null)
            throw new BadRequestException();
        task = entityFacade.createTask(task);
        logger.debug("Created task - " + task.toString());
        URI userUri = uriInfo.getAbsolutePathBuilder().path(task.getId().toString()).build();
        return Response.created(userUri).build();
    }

    @PUT
    public Response updateTask(Task task) {
        Task updatingTask = entityFacade.getTaskById(task.getId());
        if (updatingTask == null)
            throw new BadRequestException();//TODO: Implementirat validatore!
        entityFacade.updateTask(updatingTask);
        logger.debug("Updated user  - " + updatingTask.toString());
        return Response.ok(updatingTask).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTask(@PathParam("id") Integer id) {
        Task task = entityFacade.getTaskById(id);
        if (task == null)
            throw new NotFoundException();
        entityFacade.deleteTask(task);
        logger.debug("Deleted user - " + task.toString());
        return Response.noContent().build();
    }

    @GET
    @Path("search/{text}")
    public Response searchTasks(@PathParam("text") String text) {
        return Response.ok(entityFacade.searchTasks(text)).build();
    }
}
