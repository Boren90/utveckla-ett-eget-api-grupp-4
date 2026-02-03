package org.Grupp4;

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
public class CarsResource {

    @Inject
    CarsService carsService;

    @GET
    public Response getAllCars() {

        List<Cars> cars = carsService.findAll();

        if(cars.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(cars).build();
    }

    @POST
    public Response createNewCar(Cars cars) throws URISyntaxException {

        cars = carsService.createCar(cars);
        URI createdUri = new URI(cars.getVinNumber().toString());
        return Response.created(createdUri).entity(cars).build();
        
    }
    
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCarInfo(@PathParam("id") Long id, CarsDTO carsDTO) {
        System.out.println(carsDTO.getNewValue());
        Cars cars = carsService.updateCarInfo(carsDTO, id);
        return Response.ok(cars).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") Long id) {

        carsService.deleteCar(id);

        return Response.noContent().build();
    }
}
