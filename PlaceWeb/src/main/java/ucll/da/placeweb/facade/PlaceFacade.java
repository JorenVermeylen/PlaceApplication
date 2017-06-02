package ucll.da.placeweb.facade;

import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.service.ServiceException;

import java.util.List;

/**
 * Created by verme on 4/05/2017.
 */
public interface PlaceFacade {

    List<Place> getAllPlaces() throws ServiceException;

    Place getPlaceById(Long id) throws ServiceException;

    void addPlace(Place place) throws ServiceException;

    void updatePlace(Long id, Place place) throws ServiceException;

    void deletePlace(Long id) throws ServiceException;

    void generatePlaceDetailsFromPlace(Long id) throws ServiceException;

}
