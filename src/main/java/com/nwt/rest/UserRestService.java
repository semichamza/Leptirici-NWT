package com.nwt.rest;

import com.nwt.entities.User;

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
@Path ("users")
@Produces ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes ({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class UserRestService
{
    @Context
    private UriInfo uriInfo;
    @PersistenceContext (unitName = "NWTPersistenceUnit")
    private EntityManager em;

    @GET
    public Response getUsers()
    {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
        List<User> userList = new ArrayList<User>(query.getResultList());
        return Response.ok(userList).build();
    }

    @GET
    @Path ("{id}")
    public User getUser(@PathParam ("id") Integer id)
    {
        User user = em.find(User.class, id);
        if (user == null)
            throw new NotFoundException();

        return user;
//        return Response.ok(user).build();
    }

    @POST
    public Response createUser(User user)
    {
        if (user == null)
            throw new BadRequestException();

        User newUser = new User(user.getUsername(), user.getPassword(), user.getActive());
        em.persist(newUser);
        URI userUri = uriInfo.getAbsolutePathBuilder().path(newUser.getId().toString()).build();
        return Response.created(userUri).build();
    }

    @PUT
    public Response updateUser(User user)
    {
        if (user == null)
            throw new BadRequestException();

        em.merge(user);
        return Response.ok().build();
    }

    @DELETE
    @Path ("{id}")
    public Response deleteUser(@PathParam ("id") Integer id)
    {
        User user = em.find(User.class, id);
        if (user == null)
            throw new NotFoundException();
        em.remove(em.find(User.class, id));
        return Response.noContent().build();
    }

    @GET
    @Path ("{username: [a-zA-Z]*}")
    public User getUserByUsername(@PathParam ("username") String username)
    {
        User user = em.find(User.class, username);
        if (user == null)
            throw new NotFoundException();

        return user;
    }
}
