package ucll.da.placedomain.db;

import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.domain.PlaceDetails;

import java.util.List;

/**
 * Created by verme on 3/05/2017.
 */
public interface PlaceDB {

    List<Place> getAllPlaces() throws DBException;

    Place getPlaceById(Long id) throws DBException;

    void addPlace(Place place) throws DBException;

    void updatePlace(Long id, Place place) throws DBException;

    void deletePlace(Long id) throws DBException;

    void addPlaceDetailsToPlace(Long id, PlaceDetails placeDetails) throws DBException;
}
