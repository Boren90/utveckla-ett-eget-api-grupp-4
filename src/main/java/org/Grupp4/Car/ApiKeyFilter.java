package org.Grupp4.Car;

import java.io.IOException;
import java.util.UUID;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApiKeyRequired
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    @Inject
    EntityManager entitymanager;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String apiKey = requestContext.getHeaderString("X-API-KEY");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("You lack the proper Api key")
                .build());
            return;
        }

        try {


        UUID uuidApi = UUID.fromString(apiKey);

        Long userApi = entitymanager.createQuery("SELECT COUNT(u) FROM ApiUser u WHERE u.apiKey = :apiKey", Long.class)
            .setParameter("apiKey", uuidApi).getSingleResult();

        if (userApi == 0) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid API key").build());
        }

        
    } catch (IllegalArgumentException e) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
        .entity("Invalid API key format").build());
    }

}}
