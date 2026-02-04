package org.Grupp4.Car;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApiKeyRequired
public class CarResource {

    @Inject
    CarService carService;

    @GET
    @Operation(
        summary = "Hämtar alla bilar",
        description = "Hämtar listan av bilar i databasen"
    )
    @APIResponse(
        responseCode = "200",
        description = "Hittar bilar"
    )
    @APIResponse(
        responseCode = "204",
        description = "Hittar inga bilar"
    )
    public Response getAllCars() {

        List<Car> cars = carService.findAll();

        if(cars.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(cars).build();
    }

    @POST
    @Operation(
        summary = "Skapar en ny bil",
        description = "Skapar en ny bil och sparar den på en ny kolumn i databasen"
    )
    @APIResponse(
        responseCode = "201",
        description = "Bil skapas"
    )
    @APIResponse(
        responseCode = "400",
        description = "Bil kan inte skapas"
    )
    public Response createNewCar(@Valid Car car) throws URISyntaxException {

        car = carService.createCar(car);
        URI createdUri = new URI(car.getVinNumber().toString());
        return Response.created(createdUri).entity(car).build();
        
    }
    
    @PATCH
    @Operation(
        summary = "Uppdaterar bilens information",
        description = "Uppdaterar trivia och value i databasen"
    )
    @APIResponse(
        responseCode = "200",
        description = "Bilens information uppdateras"
    )
    @APIResponse(
        responseCode = "400",
        description = "Kan inte uppdatera bilen information"
    )
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCarInfo(@PathParam("id") Long id, CarDTO carsDTO) {
        System.out.println(carsDTO.getNewValue());
        Car cars = carService.updateCarInfo(carsDTO, id);
        return Response.ok(cars).build();
    }

    @DELETE
    @Operation(
        summary = "Tar bort en bil",
        description = "Raderar en kolumn av objektet bil från databasen"
    )
    @APIResponse(
        responseCode = "204",
        description = "Bilen raderas"
    )
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") Long id) {

        carService.deleteCar(id);

        return Response.noContent().build();
    }
}
