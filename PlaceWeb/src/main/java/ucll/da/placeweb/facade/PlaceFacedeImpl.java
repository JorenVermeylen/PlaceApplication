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

    @Override
    public List<Place> getAllPlaces() throws ServiceException {
        return placeService.getAllPlaces();
    }

    @Override
    public Place getPlaceById(Long id) throws ServiceException {
        return placeService.getPlaceById(id);
    }

    @Override
    public boolean addPlace(Place place) throws ServiceException {
        return placeService.addPlace(place);
    }

    @Override
    public boolean updatePlace(Long id, Place place) throws ServiceException {
        return placeService.updatePlace(id, place);
    }

    @Override
    public boolean deletePlace(Long id) throws ServiceException {
        return placeService.deletePlace(id);
    }

    @Override
    public Place generatePlaceDetailsFromPlace(Long id) throws ServiceException {
        return placeService.generatePlaceDetailsFromPlace(id);
    }
}
