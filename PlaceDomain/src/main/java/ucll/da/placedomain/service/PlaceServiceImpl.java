package ucll.da.placedomain.service;

import ucll.da.placedomain.db.DBException;
import ucll.da.placedomain.db.PlaceDB;
import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.domain.PlaceDetails;
import ucll.da.placedomain.gatherer.PlaceGatherer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import ucll.da.placedomain.domain.DomainException;

/**
 * Created by Joren on 4/05/2017.
 */
@Named
@RequestScoped
@Default
public class PlaceServiceImpl implements PlaceService {

    @Inject
    private PlaceDB placeDB;

    @Inject
    private PlaceGatherer gatherer;

    @Override
    public List<Place> getAllPlaces() throws ServiceException {
        try {
            return placeDB.getAllPlaces();
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Place getPlaceById(Long id) throws ServiceException {
        try {
            return placeDB.getPlaceById(id);
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean addPlace(Place place) throws ServiceException {
        try {
            return placeDB.addPlace(place);
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean updatePlace(Long id, Place place) throws ServiceException {
        try {
            return placeDB.updatePlace(id, place);
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean deletePlace(Long id) throws ServiceException {
        try {
            return placeDB.deletePlace(id);
        } catch (DBException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Place generatePlaceDetailsFromPlace(Long id) throws ServiceException {
        Place place = this.getPlaceById(id);
        PlaceDetails placeDetails = null;
        try {
            placeDetails = new PlaceDetails(gatherer.getNameFromGooglePlace(place.getPlaceId()), gatherer.getLongitudeFromGooglePlace(place.getPlaceId()), gatherer.getLatitudeFromGooglePlace(place.getPlaceId()), gatherer.getAddressFromGooglePlace(place.getPlaceId()), gatherer.getRatingFromGooglePlace(place.getPlaceId()), gatherer.getOpeningHoursFromGooglePlace(place.getPlaceId()));
            return placeDB.addPlaceDetailsToPlace(id, placeDetails);
        } catch (DBException | DomainException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
