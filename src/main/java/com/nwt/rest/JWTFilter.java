package com.nwt.rest;

import com.nwt.auth.entities.Token;
import com.nwt.util.AuthHelper;

import javax.ejb.Stateless;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;

@WebFilter(filterName = "jwt-filter", urlPatterns = {"/rest/*"})
@Stateless
public class JWTFilter implements Filter {
    @Context
    private UriInfo uriInfo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //ako je token istekao->exception
        Token tokenInfo = getToken((HttpServletRequest) request);

        chain.doFilter(request, response);
    }

    private Token getToken(HttpServletRequest httpRequest) throws ServletException {
        final String authorizationHeader = httpRequest.getHeader("authorization");
        if (authorizationHeader == null) {
            throw new ServletException("Unauthorized");
        }
        return AuthHelper.verifyToken(authorizationHeader);
    }

    @Override
    public void destroy() {

    }

}

