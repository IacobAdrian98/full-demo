package com.example.resource;

import com.example.model.Car;
import com.example.service.DefaultCarService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/vehicles")
public class CarResource {

    @Inject
    DefaultCarService defaultCarService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getVehiclesCount() {
        return defaultCarService.getAllCars();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(Car car, @Context UriInfo uriInfo) {
        Car savedCar = defaultCarService.addNewCar(car);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(savedCar.id));
        return Response.created(builder.build()).build();
    }

    @GET
    @Path("/{vehicleModel}-{vehicleYear}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getVehicleByModel(
            @PathParam("vehicleModel") String vehicleModel,
            @PathParam("vehicleYear") String vehicleYear
    ) {
        return defaultCarService.getCarByModelAndYear(vehicleModel, Integer.parseInt(vehicleYear));
    }

}
