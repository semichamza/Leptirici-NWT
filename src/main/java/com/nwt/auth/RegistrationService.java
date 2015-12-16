package com.nwt.auth;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nwt.auth.entities.VerificationToken;
import com.nwt.auth_entities.AuthParameterRegister;
import com.nwt.entities.ConfigConstants;
import com.nwt.entities.ResponseMessages;
import com.nwt.entities.User;
import com.nwt.entities.UserPrincipal;
import com.nwt.enums.ActionTypeEnum;
import com.nwt.enums.TokenStatusEnum;
import com.nwt.enums.UserRoleEnum;
import com.nwt.facade.EntityFacade;
import com.nwt.mailer.Mailer;
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

@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class RegistrationService {
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;
    @Inject
    @Log
    private Logger logger;


    @POST
    @Path("/register")
    public Response createUser(AuthParameterRegister aUser) {

        try{
            if (aUser == null)
                throw new BadRequestException();

            UserPrincipal principal = new UserPrincipal();
            principal.setUsername(aUser.getUsername());
            principal.setPassword(aUser.getPassword());
            principal.setUserRole(UserRoleEnum.NORMAL);

            User user = new User();
            user.setUserPrincipal(principal);
            user.setActive(false);
            user.setEmail(aUser.getEmail());
            user.setFirstName(aUser.getFirstName());
            user.setLastName(aUser.getLastName());

            user = entityFacade.createUser(user);
            logger.debug("Created user - " + user.toString());


            VerificationToken token = VerificationToken.generateToken();
            token.setActionType(ActionTypeEnum.ACTIVATION);
            token.setTokenStatus(TokenStatusEnum.ACTIVE);
            token.setUser(user);
            entityFacade.createToken(token);

            String password=entityFacade.getConfigProperty(ConfigConstants.MAIL_PASSWORD).getValue();
            String mail=entityFacade.getConfigProperty(ConfigConstants.REGISTRATION_MAIL).getValue();
            Mailer mailer=new Mailer(mail,password);
            boolean sent = mailer.sendActivationMail(user.getEmail(), token.getId());
            if (!sent)
                throw new WebApplicationException();

            return Response.status(201).build();
        }catch (Exception e)
        {
            Object message=new Object(){
                public String messageCode= "awdawd";
            };
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }


    @GET
    @Path("/activate/{token}")
    public Response activate(@PathParam("token") String hash) {

        VerificationToken token = entityFacade.getToken(hash);
        if (!isActivationTokenValid(token))
            throw new BadRequestException();

        //activate user
        User user = token.getUser();
        user.setActive(true);
        user.setBlocked(false);
        entityFacade.updateUser(user);

        //disable token
        token.setTokenStatus(TokenStatusEnum.USED);
        entityFacade.updateToken(token);
        return Response.temporaryRedirect(URI.create("/PMS-NSI")).build();
    }

    @PUT
    @Path("/reset")
    public Response reset(String jsonString) {
        try{
            JsonParser parser = new JsonParser();
            JsonObject jobj = (JsonObject) parser.parse(jsonString);

            String username = jobj.get("username").getAsString();

            User user = entityFacade.getUserByUsername(username);
            VerificationToken token = VerificationToken.generateToken();
            token.setActionType(ActionTypeEnum.PASSWORD_RECOVERY);
            token.setTokenStatus(TokenStatusEnum.ACTIVE);
            token.setUser(user);
            entityFacade.createToken(token);

            String password=entityFacade.getConfigProperty(ConfigConstants.MAIL_PASSWORD).getValue();
            String mail=entityFacade.getConfigProperty(ConfigConstants.REGISTRATION_MAIL).getValue();
            Mailer mailer=new Mailer(mail,password);
            boolean sent = mailer.sendRecoveryMail("jasmin.kaldzija@gmail.com", token.getId());
            if (!sent)
                throw new WebApplicationException();

            return Response.ok().build();
        }catch (Exception e)
        {
            return ResponseMessages.INVALID_USERNAME.getResponse();
        }
    }

    private boolean isActivationTokenValid(VerificationToken token) {
        boolean activation = token.getActionType() == ActionTypeEnum.ACTIVATION;
        boolean unused = token.getTokenStatus() == TokenStatusEnum.ACTIVE;

        return activation && unused;
    }

}
