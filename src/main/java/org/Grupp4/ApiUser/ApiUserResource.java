package org.Grupp4.ApiUser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ApiUserResource {

    @Inject
    ApiUserService userService;

    @GET
    @Operation(
        summary = "Hämtar alla användare",
        description = "Hämtar alla användare från databasen"
    )
    @APIResponse(
        responseCode = "200",
        description = "Hittar alla användare från databasen"
    )
    @APIResponse(
        responseCode = "204",
        description = "Hittade inga användare"
    )
    public Response getUser() {

        List<ApiUser> users = userService.findAll();

        if (users.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(users).build();

    }

    @POST
    @Operation(
        summary = "Skapar en användare",
        description = "Skapar en användare och sparar den i en kolumn i databasen"
    )
    @APIResponse(
        responseCode = "201",
        description = "Användare skapas"
    )
    @APIResponse(
        responseCode = "400",
        description = "Användare kan inte skapas, informationen som anges är felaktig"
    )
    public Response createUser(@Valid ApiUser apiUser) throws URISyntaxException{
        ApiUser user = userService.createNewUser(apiUser);
        URI createdUri = new URI(user.getApiKey().toString());
        return Response.created(createdUri).entity(user).build();
    } 

}