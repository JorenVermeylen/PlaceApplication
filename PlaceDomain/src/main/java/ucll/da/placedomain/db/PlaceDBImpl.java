package ucll.da.placedomain.db;

import ucll.da.placedomain.domain.Place;
import ucll.da.placedomain.domain.PlaceDetails;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import ucll.da.placedomain.domain.DomainException;

/**
 * Created by verme on 3/05/2017.
 */
@Named
@RequestScoped
@Default
public class PlaceDBImpl implements PlaceDB {

    @PersistenceContext(unitName = "PlacePUnit")
    private EntityManager em;

    @Override
    public List<Place> getAllPlaces() throws DBException {
        try {
            Query query = em.createQuery("SELECT p FROM Place p");
            return (List<Place>) query.getResultList();
        } catch (Exception e) {
            throw new DBException("Can't retrieve all the places. " + e.getMessage());
        }
    }

    @Override
    public Place getPlaceById(Long id) throws DBException {
        try {
            return em.find(Place.class, id);
        } catch (Exception e) {
            throw new DBException("Can't retrieve the place with this id. " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void addPlace(Place place) throws DBException {
        try {
            em.persist(place);
            em.flush();
        } catch (Exception e) {
            throw new DBException("Can't add the place. " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void updatePlace(Long id, Place place) throws DBException {
        try {
            Query query = em.createQuery("UPDATE Place p SET p.placeId = :placeId, p.date = :date, p.placeDetails = :placeDetails WHERE p.id = :id");
            query.setParameter("id", id);
            query.setParameter("placeId", place.getPlaceId());
            query.setParameter("date", place.getDate());
            query.setParameter("placeDetails", place.getPlaceDetails());
            query.executeUpdate();
        } catch (Exception e) {
            throw new DBException("Can't update the place. " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deletePlace(Long id) throws DBException {
        try {
            Place place = this.getPlaceById(id);
            em.remove(place);
            em.flush();
        } catch (DBException e) {
            throw new DBException("Can't delete the place. " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public void addPlaceDetailsToPlace(Long id, PlaceDetails placeDetails) throws DBException {
        try {
            Place place = this.getPlaceById(id);
            place.setPlaceDetails(placeDetails);
            em.persist(place);
            em.flush();
        } catch (DBException | DomainException e) {
            throw new DBException("Can't add the placeDetails. " + e.getMessage());
        }
    }
}
