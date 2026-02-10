package org.Grupp4.Car;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
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
        summary = "Gets all cars",
        description = "Gets the list of all existing cars in the database"
    )
    @APIResponse(
        responseCode = "200",
        description = "Found cars"
    )
    @APIResponse(
        responseCode = "204",
        description = "Could not find cars"
    )
    public Response getAllCars() {
        List<Car> cars = carService.findAll();
        if(cars.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(cars).build();
    }

    @GET
    @Path("/sort")
    public Response sortByModel() {
          List<Car> cars = carService.findAll();
        if(cars.isEmpty()) {
            return Response.noContent().build();
        }
        
        cars.sort(Comparator.comparing(Car::getCarModel));
    
        return Response.ok(cars).build();
    }

    @GET
    @Path("/search")
    @Operation(
        summary = "Fetches all cars of a specific brand",
        description = "Fetching the list of cars the user is searching for"
    )
    @APIResponse(
        responseCode = "200",
        description = "Found cars"
    )
    @APIResponse(
        responseCode = "204",
        description = "Could not find cars"
    )
    public Response getCarsByBrand(Car car) {
        List<Car> cars = carService.findByCarBrand(car);
        if(cars.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("This brand does not exist, did you spell it right?").build();
        }
        return Response.ok(cars).build();
    }

    @POST
    @Operation(
        summary = "Creates a new car",
        description = "Creates a new car, and saves it to a new colum in the database"
    )
    @APIResponse(
        responseCode = "201",
        description = "A car has been created"
    )
    @APIResponse(
        responseCode = "400",
        description = "A car could not be created"
    )
    public Response createNewCar(@Valid Car car) throws URISyntaxException {

        car = carService.createCar(car);
        URI createdUri = new URI(car.getVinNumber().toString());
        return Response.created(createdUri).entity(car).build();
        
    }
    
    @PATCH
    @Operation(
        summary = "Updates the car information",
        description = "Updates the trivia and value in the database"
    )
    @APIResponse(
        responseCode = "200",
        description = "The cars information updated"
    )
    @APIResponse(
        responseCode = "400",
        description = "Could not update the car information"
    )
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCarInfo(@PathParam("id") Long id, CarDTO carDTO) {
        System.out.println(carDTO.getNewValue());
        Car car = carService.updateCarInfo(carDTO, id);
        return Response.ok(car).build();
    }

    @DELETE
    @Operation(
        summary = "Removes a car",
        description = "Deletes an existing car from the database"
    )
    @APIResponse(
        responseCode = "204",
        description = "Car deleted"
    )
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") Long id) {

        carService.deleteCar(id);

        return Response.noContent().build();
    }
}
