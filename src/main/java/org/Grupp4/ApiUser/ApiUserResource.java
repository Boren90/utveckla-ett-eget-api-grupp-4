package org.Grupp4.ApiUser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/key")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiUserResource {

    @Inject
    ApiUserService userService;

    @GET
    public Response getAllUsers() {

        List<ApiUser> users = userService.findAll();

        if (users.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(users).build();

    }

    @POST
    public Response createUser(@Valid ApiUser apiUser) throws URISyntaxException{
        ApiUser user = userService.createNewUser(apiUser);
        URI createdUri = new URI(user.getApiKey().toString());
        return Response.created(createdUri).entity(user).build();
    } 

}
// System.out.println(apiKey);
// userService.getApiKey(apiKey);
// return Response.ok(apiKey).build();