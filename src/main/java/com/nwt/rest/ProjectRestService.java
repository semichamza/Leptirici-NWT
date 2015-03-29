package com.nwt.rest;

import com.nwt.entities.Project;
import com.nwt.entities.Projects;
import com.nwt.facade.EntityFacade;
import com.nwt.util.Log;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path ("projects")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class ProjectRestService
{
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;

    @GET
    public Response getAllProjects()
    {
        Projects projects = entityFacade.getAllProjects();
        logger.debug("getAllProjects() returned " + projects.size() + " object(s).");
        return Response.ok(projects).build();
    }

    @GET
    @Path ("{id}")
    public Response getProjectById(@PathParam ("id") Integer id)
    {
        Project project = entityFacade.getProjectById(id);
        if (project == null)
            throw new NotFoundException();
        logger.debug("getProjectById() returned: " + project.toString());
        return Response.ok(project).build();
    }

    @POST
    public Response createProject(Project project)
    {
        if (project == null)
            throw new BadRequestException();
        project = entityFacade.createProject(project);
        logger.debug("Created project - " + project.toString());
        URI projectUri = uriInfo.getAbsolutePathBuilder().path(project.getId().toString()).build();
        return Response.created(projectUri).build();
    }

    @PUT
    public Response updateProject(Project project)
    {
        Project updatingProject = entityFacade.getProjectById(project.getId());
        if (updatingProject == null)
            throw new BadRequestException();//TODO: Implementirat validatore!
        entityFacade.updateProject(updatingProject);
        logger.debug("Updated project  - " + updatingProject.toString());
        return Response.ok(updatingProject).build();
    }

    @DELETE
    @Path ("{id}")
    public Response deleteProject(@PathParam ("id") Integer id)
    {
        Project project = entityFacade.getProjectById(id);
        if (project == null)
            throw new NotFoundException();
        entityFacade.deleteProject(project);
        logger.debug("Deleted project - " + project.toString());
        return Response.noContent().build();
    }

    @GET
    @Path ("search/{text}")
    public Response searchProjects(@PathParam ("text") String text)
    {
        return Response.ok(entityFacade.searchProjects(text)).build();
    }
}
