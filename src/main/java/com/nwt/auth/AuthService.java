package com.nwt.auth;

import com.nwt.entities.ConfigConstants;
import com.nwt.entities.ResponseMessages;
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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class AuthService {
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;

    static byte[] bytes;
    static byte[] bytes1;

    @POST
    public Response login(AuthParameter authParameter) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("NWTBundle");
        AuthResponseObject auth = new AuthResponseObject();
        try {

            User user = entityFacade.getUserByUsername(authParameter.getUsername());
            UserPrincipal principal = user.getUserPrincipal();
            if (principal == null ||!user.getActive() || !principal.getPasswordHash()
                    .equals(DigestUtils.md5Hex(authParameter.getPassword())))
            {
                return ResponseMessages.INVALID_LOGIN.getResponse();
            }else if(user.getDeleted())
            {
                return ResponseMessages.USER_DELETED.getResponse();
            }
            else if(user.getBlocked())
            {
                return ResponseMessages.USER_BLOCKED.getResponse();
            }
            auth.setJwt(AuthHelper.createJsonWebToken(principal.getUsername(), principal.getPasswordHash(), 999999999L));
            auth.setUser(user);
            return Response.ok(auth).build();

        } catch (Exception e) {
            return ResponseMessages.INVALID_LOGIN.getResponse();
        }
    }

    @GET
    @Path("/mail")
    public Response sentMail() {
        MessageBody messageBody = new MessageBody("Test", "Test");
        messageBody.addParagraph("TestP");

        String password=entityFacade.getConfigProperty(ConfigConstants.MAIL_PASSWORD).getValue();
        String mail=entityFacade.getConfigProperty(ConfigConstants.REGISTRATION_MAIL).getValue();
        Mailer mailer=new Mailer(mail,password);
        boolean send = mailer.sendEmail("jasmin.kaldzija@gmail.com", messageBody);

        return Response.ok().build();
    }
}
