package org.Grupp4.ApiUser;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiUserResource {

    @Inject
    ApiUserService userService;

    @GET
    @Operation(summary = "Fetches all users", description = "Fetches all users from the database")
    @APIResponse(responseCode = "200", description = "Find all users from the database")
    @APIResponse(responseCode = "204", description = "No users found")
    public Response getUser(ApiUser apiUser) {

        ApiUser user = userService.findByUsernameAndPassword(apiUser);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not find password or username").build();
        }

        return Response.ok(user).build();

    }

    @POST
    @Operation(summary = "Creating a user", description = "Creating a user and saving it in the database")
    @APIResponse(responseCode = "201", description = "User created")
    @APIResponse(responseCode = "400", description = "User could not be created, the information provided is incorrect")
    public Response createUser(@Valid ApiUser apiUser) throws URISyntaxException {
        ApiUser user = userService.createNewUser(apiUser);
        URI createdUri = new URI(user.getApiKey().toString());
        return Response.created(createdUri).entity(user).build();
    }

}