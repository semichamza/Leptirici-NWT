package com.nwt.rest;

import com.nwt.entities.Project;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glasshark on 23-Mar-15.
 */
@Path ("/projects")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class ProjectRestService
{
    @Context
    private UriInfo uriInfo;
    @PersistenceContext (unitName = "NWTPersistenceUnit")
    private EntityManager em;

    @GET
    public Response getProjects()
    {
        TypedQuery<Project> query = em.createNamedQuery(Project.FIND_ALL, Project.class);
        List<Project> projectList = new ArrayList(query.getResultList());
        return Response.ok(projectList).build();
    }

    @GET
    @Path ("{id}")
    public Response getProject(@PathParam ("id") Integer id)
    {
        Project project = em.find(Project.class, id);

        if (project == null)
            throw new NotFoundException();

        return Response.ok(project).build();
    }

    @POST
    public Response createProject(Project project)
    {
        if (project == null)
            throw new BadRequestException();

        em.persist(project);
        URI projectUri = uriInfo.getAbsolutePathBuilder().path(project.getId().toString()).build();
        return Response.created(projectUri).build();
    }

    @PUT
    public Response updateBook(Project project)
    {
        if (project == null)
            throw new BadRequestException();

        em.merge(project);
        return Response.ok().build();
    }

    @DELETE
    @Path ("{id}")
    public Response deleteBook(@PathParam ("id") Integer id)
    {
        Project project = em.find(Project.class, id);
        if (project == null)
            throw new NotFoundException();
        em.remove(em.find(Project.class, id));
        return Response.noContent().build();
    }

    @GET
    @Path ("search/{text}")
    public Response searchProjects(@PathParam ("text") String text)
    {
        //TODO
        return Response.noContent().build();
    }


}
