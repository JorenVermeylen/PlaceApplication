package ucll.da.placedomain.db;

import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.domain.PlaceDetails;

import java.util.List;

/**
 * Created by Joren on 3/05/2017.
 */
public interface PlaceDB {

    List<Place> getAllPlaces() throws DBException;

    Place getPlaceById(Long id) throws DBException;

    boolean addPlace(Place place) throws DBException;

    boolean updatePlace(Long id, Place place) throws DBException;

    boolean deletePlace(Long id) throws DBException;

    Place addPlaceDetailsToPlace(Long id, PlaceDetails placeDetails) throws DBException;
}
