package com.nwt.auth;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nwt.auth.entities.VerificationToken;
import com.nwt.auth_entities.AuthParameterRegister;
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

@Path("user")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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

        if (aUser == null)
            throw new BadRequestException();

        UserPrincipal principal = new UserPrincipal();
        principal.setUsername(aUser.getUsername());
        principal.setPassword(aUser.getPassword());
        principal.setUserRole(UserRoleEnum.NORMAL.getRole());

        User user = new User();
        user.setUserPrincipal(principal);
        user.setActive(false);
        user.setEmail(aUser.getEmail());
        user.setFirstName(aUser.getName());
        user.setLastName(aUser.getLastname());

        user = entityFacade.createUser(user);
        logger.debug("Created user - " + user.toString());


        VerificationToken token = VerificationToken.generateToken();
        token.setActiontTypeId(ActionTypeEnum.ACTIVATION.getId());
        token.setTokenStatusId(TokenStatusEnum.ACTIVE.getId());
        token.setUser(user);
        entityFacade.createToken(token);


        boolean sent = Mailer.sendActivationMail(user.getEmail(), token.getId());
        if (!sent)
            throw new WebApplicationException();

        return Response.status(201).build();

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
        entityFacade.updateUser(user);

        //disable token
        token.setTokenStatus(TokenStatusEnum.USED);
        entityFacade.updateToken(token);
        return Response.ok().build();
    }

    @PUT
    @Path("/reset")
    public Response reset(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject jobj = (JsonObject) parser.parse(jsonString);

        String username = jobj.get("username").getAsString();

        User user = entityFacade.getUserByUsername(username);
        VerificationToken token = VerificationToken.generateToken();
        token.setActiontTypeId(ActionTypeEnum.PASSWORD_RECOVERY.getId());
        token.setTokenStatusId(TokenStatusEnum.ACTIVE.getId());
        token.setUser(user);
        entityFacade.createToken(token);


        boolean sent = Mailer.sendRecoveryMail("jasmin.kaldzija@gmail.com", token.getId());
        if (!sent)
            throw new WebApplicationException();

        return Response.ok().build();
    }

    ;


    private boolean isActivationTokenValid(VerificationToken token) {
        boolean activation = token.getActiontTypeId() == ActionTypeEnum.ACTIVATION.getId();
        boolean unused = token.getTokenStatusId() == TokenStatusEnum.ACTIVE.getId();

        return activation && unused;
    }

}
