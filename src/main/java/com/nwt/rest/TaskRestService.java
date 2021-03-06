package com.nwt.rest;

import com.nwt.entities.Comments;
import com.nwt.entities.Logs;
import com.nwt.entities.Task;
import com.nwt.entities.Tasks;
import com.nwt.enums.TaskStatusEnum;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
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
    @Path("/{id}")
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
        task.setTaskStatus(TaskStatusEnum.OPEN);
        task = entityFacade.createTask(task);
        logger.debug("Created task - " + task.toString());
        return Response.status(Response.Status.CREATED).entity(task).build();
    }

    @PUT
    public Response updateTask(Task task) {
        Task existingTask = entityFacade.getTaskById(task.getId());
        if (existingTask == null)
            throw new BadRequestException();//TODO: Implementirat validatore!
        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setTaskPriority(task.getTaskPriority());
        existingTask.setTaskStatus(task.getTaskStatus());
        entityFacade.updateTask(existingTask);
        logger.debug("Updated user  - " + existingTask.toString());
        return Response.ok(existingTask).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") Integer id) {
        Task task = entityFacade.getTaskById(id);
        if (task == null)
            throw new NotFoundException();
        entityFacade.deleteTask(task);
        logger.debug("Deleted user - " + task.toString());
        return Response.noContent().build();
    }

    @GET
    @Path("/search/{text}")
    public Response searchTasks(@PathParam("text") String text) {
        return Response.ok(entityFacade.searchTasks(text)).build();
    }

    @GET
    @Path ("/{id}/comments")
    public Response getAllTaskComments(@PathParam ("id") Integer id)
    {
        Task task = entityFacade.getTaskById(id);
        if (task == null)
            throw new NotFoundException();
        Comments comments = new Comments(task.getComments());
        logger.debug("getAllTaskComments() for taskId: " + id + " returned: " + comments.size() + "results");
        return Response.ok(comments).build();
    }

    @GET
    @Path ("/{taskId}/comments/{commentId}")
    public Response getTaskCommentById(@PathParam ("taskId") Integer taskId, @PathParam ("commentId") Integer commentId)
    {
        //TODO Implement!
        logger.debug("getTaskCommentById() not implemented yet");
        return null;
    }

    @GET
    @Path ("/{id}/logs")
    public Response getAllTaskLogs(@PathParam ("id") Integer id)
    {
        Task task = entityFacade.getTaskById(id);
        if (task == null)
            throw new NotFoundException();
        Logs logs = new Logs(task.getLogs());
        logger.debug("getAllTaskLogs() for taskId: " + id + " returned: " + logs.size() + "results");
        return Response.ok(logs).build();
    }

    @GET
    @Path ("/{taskId}/logs/{logId}")
    public Response getTaskLogById(@PathParam ("taskId") Integer taskId, @PathParam ("logId") Integer logId)
    {
        //TODO Implement!
        logger.debug("getTaskLogById() not implemented yet");
        return null;
    }

    @GET
    @Path("/users")
    public Response getUserTasks(@QueryParam("userId") int userId,@QueryParam("projectId") int projectId)
    {
        Tasks tasks=entityFacade.getUserTasks(userId,projectId);
        return Response.status(Response.Status.OK).entity(tasks).build();
    }

    @PUT
    @Path("/{taskId}/users/{userId}/assignee")
    public Response assigneeTask(@PathParam("taskId") Integer taskId,@PathParam("userId") Integer userId) {
        Task task=entityFacade.getTaskById(taskId);
        task.setUser(entityFacade.getUserById(userId));
        entityFacade.updateTask(task);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}/status/{status}")
    public Response updateTaskStatus(@PathParam("id") Integer taskId,@PathParam("status") String status) {
        Task existingTask = entityFacade.getTaskById(taskId);
        existingTask.setTaskStatus(TaskStatusEnum.getStatus(status));
        return Response.ok(existingTask).build();
    }
}
