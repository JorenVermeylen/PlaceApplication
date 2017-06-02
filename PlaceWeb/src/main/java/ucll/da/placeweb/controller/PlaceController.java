package ucll.da.placeweb.controller;


import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.service.ServiceException;
import ucll.da.placeweb.facade.PlaceFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by verme on 3/05/2017.
 */
@Named
@RequestScoped
@Path("/place")
public class PlaceController {

    @Inject
    PlaceFacade facade;

    public PlaceController() {

    }

    @GET
    @Path("/health")
    public String getHealth() {
        return "Up & Running!";
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Place getPlace(@PathParam("id") long id) throws ServiceException {
        return facade.getPlaceById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Place> getAllPlaces() throws ServiceException {
        return facade.getAllPlaces();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPlace(Place place) throws ServiceException {
        facade.addPlace(place);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void updatePlace(@PathParam("id") long id, Place place) throws ServiceException {
        facade.updatePlace(id, place);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void deletePlace(@PathParam("id") Long id) throws ServiceException {
        facade.deletePlace(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/generatePlaceDetails/{id}")
    public void generatePlaceDetailsFromPlace(@PathParam("id") Long id) throws ServiceException {
        facade.generatePlaceDetailsFromPlace(id);
    }
}
