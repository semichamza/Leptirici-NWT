package com.nwt.auth;

import com.nwt.auth.entities.VerificationToken;
import com.nwt.entities.ConfigConstants;
import com.nwt.entities.User;
import com.nwt.enums.ActionTypeEnum;
import com.nwt.enums.TokenStatusEnum;
import com.nwt.facade.EntityFacade;
import com.nwt.mailer.Mailer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Jasmin on 15-Apr-15.
 */
@WebServlet("/resetConfirmation")
@Stateless
public class ResetConfirmServlet extends HttpServlet {
    @Context
    private UriInfo uriInfo;
    @Inject
    private EntityFacade entityFacade;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tokenId = request.getParameter("id");
        if (tokenId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("404.html");
        }
        VerificationToken token = entityFacade.getToken(tokenId);

        if (!isResetTokenValid(token)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("404.html");
        }

        String newPass = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        User user = token.getUser();
        user.getUserPrincipal().setPassword(newPass);
        entityFacade.updateUser(user);

        String password=entityFacade.getConfigProperty(ConfigConstants.MAIL_PASSWORD).getValue();
        String mail=entityFacade.getConfigProperty(ConfigConstants.REGISTRATION_MAIL).getValue();
        String appURL=entityFacade.getConfigProperty(ConfigConstants.APP_URL).getValue();

        Mailer mailer=new Mailer(mail,password,appURL);
        boolean sent = mailer.sendNewPassword(user.getEmail(), newPass);

        if (sent) {
            token.setTokenStatus(TokenStatusEnum.USED);
            entityFacade.updateToken(token);
        }

        response.sendRedirect("#/reset");
    }

    private boolean isResetTokenValid(VerificationToken token) {
        boolean activation = token.getActionType() == ActionTypeEnum.PASSWORD_RECOVERY;
        boolean unused = token.getTokenStatus() == TokenStatusEnum.ACTIVE;

        return activation && unused;
    }
}
