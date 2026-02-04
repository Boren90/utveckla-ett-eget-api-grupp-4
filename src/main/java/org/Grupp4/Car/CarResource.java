package org.Grupp4.Car;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

@Path("/api/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApiKeyRequired
public class CarResource {

    @Inject
    CarService carService;

    @GET
    // @Operation(
    //     summary = "summary",
    //     description = "description"
    // )
    public Response getAllCars() {

        List<Car> cars = carService.findAll();

        if(cars.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(cars).build();
    }

    @POST
    public Response createNewCar(@Valid Car car) throws URISyntaxException {

        car = carService.createCar(car);
        URI createdUri = new URI(car.getVinNumber().toString());
        return Response.created(createdUri).entity(car).build();
        
    }
    
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCarInfo(@PathParam("id") Long id, CarDTO carsDTO) {
        System.out.println(carsDTO.getNewValue());
        Car cars = carService.updateCarInfo(carsDTO, id);
        return Response.ok(cars).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") Long id) {

        carService.deleteCar(id);

        return Response.noContent().build();
    }
}
