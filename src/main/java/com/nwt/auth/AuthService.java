package com.nwt.auth;

import com.nwt.auth.entities.AuthParameter;
import com.nwt.auth.entities.AuthResponseObject;
import com.nwt.entities.UserPrincipal;
import com.nwt.facade.EntityFacade;
import com.nwt.util.AuthHelper;
import com.nwt.util.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ResourceBundle;

@Path("login")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class AuthService {
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;


    @POST
    public Response login(AuthParameter authParameter) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("NWTBundle");
        AuthResponseObject auth = new AuthResponseObject();
        try {

            UserPrincipal principal = entityFacade.getUserByUsername(authParameter.getUsername()).getUserPrincipal();
            if (principal == null || !principal.getPasswordHash()
                    .equals(DigestUtils.md5Hex(authParameter.getPassword())))
            {

                auth.setMessage(resourceBundle.getString("messageError"));
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(auth).build();
            }
            //999999999L trajanje tokena u minutama
            auth.setJwt(
                    AuthHelper.createJsonWebToken(principal.getUsername(), principal.getPasswordHash(), 999999999L));
            auth.setMessage(resourceBundle.getString("messageSuccess"));
            auth.setIsAutorized(true);

            return Response.ok(auth).build();

        } catch (Exception e) {
            auth.setMessage(resourceBundle.getString("messageException"));
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(auth).build();
        }
    }
}
