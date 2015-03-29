//package com.nwt.rest;
//
//import com.nwt.entities.Task;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by glasshark on 23-Mar-15.
// */
//@Path ("tasks")
//@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//@Stateless
//public class TaskRestService
//{
//    @Context
//    private UriInfo uriInfo;
//    @PersistenceContext (unitName = "NWTPersistenceUnit")
//    private EntityManager em;
//
//    @GET
//    public Response getTasks()
//    {
////        TypedQuery<Task> query = em.createNamedQuery(Task.FIND_ALL, Task.class);
////        List<Task> taskList = new ArrayList(query.getResultList());
////        return Response.ok(taskList).build();
//        return null;
//    }
//
//    @GET
//    @Path ("{id}")
//    public Response getTask(@PathParam ("id") Integer id)
//    {
//        Task task = em.find(Task.class, id);
//
//        if (task == null)
//            throw new NotFoundException();
//
//        return Response.ok(task).build();
//    }
//
//    @POST
//    public Response createTask(Task task)
//    {
//        if (task == null)
//            throw new BadRequestException();
//
//        em.persist(task);
//        URI projectUri = uriInfo.getAbsolutePathBuilder().path(task.getId().toString()).build();
//        return Response.created(projectUri).build();
//    }
//
//    @PUT
//    public Response updateBook(Task task)
//    {
//        if (task == null)
//            throw new BadRequestException();
//
//        em.merge(task);
//        return Response.ok().build();
//    }
//
//    @DELETE
//    @Path ("{id}")
//    public Response deleteBook(@PathParam ("id") Integer id)
//    {
//        Task task = em.find(Task.class, id);
//        if (task == null)
//            throw new NotFoundException();
//        em.remove(em.find(Task.class, id));
//        return Response.noContent().build();
//    }
//
//    @GET
//    @Path ("search/{text}")
//    public Response searchTasks(@PathParam ("text") String text)
//    {
//        //TODO
//        return Response.noContent().build();
//    }
//
//
//}
