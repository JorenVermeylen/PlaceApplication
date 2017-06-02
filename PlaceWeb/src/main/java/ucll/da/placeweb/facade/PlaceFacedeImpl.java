package ucll.da.placeweb.facade;

import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.service.PlaceService;
import ucll.da.placedomain.service.ServiceException;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by verme on 4/05/2017.
 */
@Named
@RequestScoped
@Default
public class PlaceFacedeImpl implements PlaceFacade {

    @Inject
    private PlaceService placeService;

    public List<Place> getAllPlaces() throws ServiceException {
        return placeService.getAllPlaces();
    }

    public Place getPlaceById(Long id) throws ServiceException {
        return placeService.getPlaceById(id);
    }

    public void addPlace(Place place) throws ServiceException {
        placeService.addPlace(place);
    }

    public void updatePlace(Long id, Place place) throws ServiceException {
        placeService.updatePlace(id, place);
    }

    public void deletePlace(Long id) throws ServiceException {
        placeService.deletePlace(id);
    }

    public void generatePlaceDetailsFromPlace(Long id) throws ServiceException {
        placeService.generatePlaceDetailsFromPlace(id);
    }
}
