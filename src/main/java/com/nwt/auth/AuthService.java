package com.nwt.auth;

import com.nwt.auth.entities.AuthParameter;
import com.nwt.auth.entities.AuthResponseObject;
import com.nwt.entities.User;
import com.nwt.entities.UserPrincipal;
import com.nwt.facade.EntityFacade;
import com.nwt.mailer.Mailer;
import com.nwt.mailer.MessageBody;
import com.nwt.util.AuthHelper;
import com.nwt.util.Log;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
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

            User user = entityFacade.getUserByUsername(authParameter.getUsername());
            UserPrincipal principal = user.getUserPrincipal();
            if (principal == null || !user.getActive() || !principal.getPasswordHash()
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
            auth.setName(principal.getUsername());
            return Response.ok(auth).build();

        } catch (Exception e) {
            auth.setMessage(resourceBundle.getString("messageError"));
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(auth).build();
        }
    }

    @GET
    @Path("/mail")
    public Response sentMail() {
        MessageBody messageBody = new MessageBody("Test", "Test");
        messageBody.addParagraph("TestP");

        boolean send = Mailer.sendEmail("jasmin.kaldzija@gmail.com", messageBody);

        return Response.ok().build();
    }
}
