package ucll.da.placedomain.service;

import ucll.da.placedomain.domain.Place;

import java.util.List;

/**
 * Created by Joren on 4/05/2017.
 */
public interface PlaceService {

    List<Place> getAllPlaces() throws ServiceException;

    Place getPlaceById(Long id) throws ServiceException;

    boolean addPlace(Place place) throws ServiceException;

    boolean updatePlace(Long id, Place place) throws ServiceException;

    boolean deletePlace(Long id) throws ServiceException;

    Place generatePlaceDetailsFromPlace(Long id) throws ServiceException;
}
