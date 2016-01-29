package com.nwt.rest;

import com.nwt.entities.*;
import com.nwt.enums.ProjectRoleEnum;
import com.nwt.facade.EntityFacade;
import com.nwt.util.CollectionUtil;
import com.nwt.util.Log;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.hibernate.mapping.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by glasshark on 23-Mar-15.
 */
@Path ("projects")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
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
    @Path("/{id}")
    public Response getProjectById(@PathParam ("id") Integer id)
    {
        Project project = projectById(id);
        logger.debug("getProjectById() returned: " + project.toString());
        return Response.ok(project).build();
    }

    @GET
    @Path("/{id}/report")
    @Produces("application/pdf")
    public Response getProjectReport(@PathParam ("id") Integer id)
    {
        Project project=projectById(id);
        String fileName = "Report.pdf"; // name of our file
        Users users = entityFacade.getProjectUsers(project);
        User owner=null;
        for(ProjectUser projectUser:project.getProjectUsers())
        {
            if(projectUser.getProjectRole().equals(ProjectRoleEnum.OWNER))
                owner=projectUser.getUser();
        }

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        Tasks tasks= entityFacade.getProjectTasks(id);
        doc.addPage(page);

        try {
            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 26);
            content.moveTextPositionByAmount(220, 700);
            content.drawString("Project statistics");
            content.endText();


            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.moveTextPositionByAmount(80, 650);
            content.drawString("Name : "+project.getName());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.moveTextPositionByAmount(80, 600);
            content.drawString("Project owner : "+owner.getLastName()+" "+owner.getFirstName());
            content.endText();


            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.moveTextPositionByAmount(80,550);
            content.drawString("Number of members: "+users.size());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.moveTextPositionByAmount(80,500);
            content.drawString("Number of tasks : "+entityFacade.getProjectTasks(id).size());
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 16);
            content.moveTextPositionByAmount(80,450);
            content.drawString("Completed : 0.0%");
            content.endText();

            content.close();
            doc.save(fileName);
            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(fileName);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=report.pdf");
        return response.build();
    }

    private Project projectById(Integer id)
    {
        Project project = entityFacade.getProjectById(id);
        if (project == null)
            throw new NotFoundException();
        return project;
    }

    @POST
    public Response createProject(Project project)
    {
        if (project == null)
            throw new BadRequestException();
        project = entityFacade.createProject(project);
        logger.debug("Created project - " + project.toString());

        return Response.ok().entity(project).build();
    }

    @PUT
    public Response updateProject(Project project)
    {
            Project existingProject = entityFacade.getProjectById(project.getId());
        if (existingProject == null)
            throw new BadRequestException();//TODO: Implementirat validatore!
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        entityFacade.updateProject(existingProject);
        logger.debug("Updated project  - " + project.toString());
        Users users = entityFacade.getProjectUsers(project);
        User sender=null;
        for(ProjectUser projectUser:existingProject.getProjectUsers())
        {
            if(projectUser.getProjectRole().equals(ProjectRoleEnum.OWNER))
                sender=projectUser.getUser();
        }

        return Response.ok(project).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProject(@PathParam ("id") Integer id)
    {
        Project project = entityFacade.getProjectById(id);
        if (project == null)
            throw new NotFoundException();
        entityFacade.deleteProject(project);
        logger.debug("Deleted project - " + project.toString());
        return Response.noContent().build();
    }

    @POST
    @Path ("/{id}/addUser")
    public Response addUserToProject(@PathParam ("id") Integer id, @QueryParam ("userId") Integer userId,
                                     @DefaultValue ("MEMBER") @QueryParam ("projectRole") ProjectRoleEnum projectRole)
    {
        Project project = entityFacade.getProjectById(id);
        if (project == null)
            throw new NotFoundException();
        User user = entityFacade.getUserById(userId);
        if (user == null)
            throw new NotFoundException("User not found");
        //is valid role
        project.addUser(user, projectRole);
        updateProject(project);

        Users users = entityFacade.getProjectUsers(project);
        User sender=null;
        for(ProjectUser projectUser:project.getProjectUsers())
        {
            if(projectUser.getProjectRole().equals(ProjectRoleEnum.OWNER))
                sender=projectUser.getUser();
        }

        return Response.ok().build();
    }

    @GET
    @Path("/search/{text}")
    public Response searchProjects(@PathParam ("text") String text)
    {
        return Response.ok(entityFacade.searchProjects(text)).build();
    }


    @GET
    @Path ("/{id}/users")
    public Response getAllProjectUsers(@PathParam ("id") Integer id)
    {
        Project project = projectById(id);
        Users users = entityFacade.getProjectUsers(project);
        logger.debug("getAllProjectUsers() returned " + users.size() + " object(s).");
        return Response.ok(users).build();
    }

    @GET
    @Path ("/{id}/users/free")
    public Response getFreeUsers(@PathParam ("id") Integer id)
    {
        Project project = projectById(id);
        Users users = entityFacade.getFreeUsers(project);
        logger.debug("getAllProjectUsers() returned " + users.size() + " object(s).");
        return Response.ok(users).build();
    }

    @GET
    @Path ("/{id}/tasks")
    public Response getAllProjectTasks(@PathParam ("id") Integer id)
    {
        Tasks tasks = entityFacade.getProjectTasks(id);
        logger.debug("getAllTasks() returned " + tasks.size() + " object(s).");
        return Response.ok(tasks).build();
    }

    @GET
    @Path ("/{id}/tasks/{order}")
    public Response getAllProjectTasksOrdered(@PathParam ("id") Integer id,@PathParam("order") String order)
    {
        Tasks tasks = entityFacade.getProjectTasks(id);
        Object[] taskArray=tasks.toArray();
        for(int i=0;i<taskArray.length;i++)
        {
            for(int j=0;j<taskArray.length;j++)
            {
                Task task=null;
                if(order.equals("PRIORITY"))
                {
                    if(((Task)taskArray[i]).priorityLevel()>((Task)taskArray[j]).priorityLevel())
                    {
                        task=(Task) taskArray[i];
                        taskArray[i]=taskArray[j];
                        taskArray[j]=task;
                    }
                }
                else if(order.equals("DUE_DATE"))
                {
                    if(((Task)taskArray[i]).dueDateObject().before(((Task)taskArray[j]).dueDateObject()))
                    {
                        task=(Task)taskArray[i];
                        taskArray[i]=taskArray[j];
                        taskArray[j]=task;
                    }
                }
            }
        }
        tasks=new Tasks();
        Collections.addAll((java.util.Collection)tasks,taskArray);
        logger.debug("getAllTasks() returned " + tasks.size() + " object(s).");
        return Response.ok(tasks).build();
    }
    @PUT
    @Path( "/{id}/users/{userId}/delete")
    public Response deleteUserFromProfjec(@PathParam("id") Integer projectId,@PathParam("userId") Integer userId )
    {
        Project project = entityFacade.getProjectById(projectId);
        if (project == null)
            throw new NotFoundException();
        entityFacade.removeUserFromProject(project.getProjectUser(userId));

        return Response.ok().build();
    }

    @GET
    @Path ("/{id}/users/{userId}/role")
    public Response getUserRole(@PathParam("id") Integer projectId,@PathParam("userId") Integer userId)
    {
        Project project = projectById(projectId);
        for(ProjectUser projectUser:project.getProjectUsers())
        {
            if(projectUser.getUserId().equals(userId))
                return Response.status(Response.Status.ACCEPTED).entity(projectUser.getProjectRole()).build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path ("/{id}/close")
    public Response getClose(@PathParam("id") Integer projectId)
    {
        Project project = projectById(projectId);
        project.setClosed(true);
        entityFacade.updateProject(project);
        return Response.ok().build();
    }
}