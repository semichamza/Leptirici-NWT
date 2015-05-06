package com.nwt.rest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by glasshark on 04-May-15.
 */
@Path ("tests")
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
@Stateless
public class EntityManagerStubService
{
    @PersistenceContext (unitName = "NWTPersistenceUnit")
    private EntityManager em;

    @GET
    @Path ("/flush")
    public Response flush()
    {
        em.flush();
        return Response.ok().build();
    }
}
