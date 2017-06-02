package ucll.da.placedomain.domain;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Joren on 16-5-2017.
 */
@Named
@Entity
public class PlaceDetails {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;

    private double longitude;
    private double latitude;

    private String address;
    private double rating;
    private String openingHours;

    public PlaceDetails() {

    }

    public PlaceDetails(String name, double longitude, double latitude, String address, double rating, String openingHours) throws DomainException {
        this.setName(name);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setAddress(address);
        this.setRating(rating);
        this.setOpeningHours(openingHours);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) throws DomainException{
        if(name == null || name.equals("")){
            throw new DomainException("Name can't be empty.");
        }
        this.name = name;
    }
    
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) throws DomainException {
        if (longitude < -180 || longitude > 180) {
            throw new DomainException("Longitude must be between -180° and 180°.");
        }
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) throws DomainException {
        if (latitude < -90 || latitude > 90) {
            throw new DomainException("Latitude must be between -90° and 90°.");
        }
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws DomainException {
        if (address == null || address.equals("")) {
            throw new DomainException("Address can't be null or empty.");
        }
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) throws DomainException {
        if (rating < 0) {
            throw new DomainException("Rating can't be smaller than zero.");
        }
        this.rating = rating;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) throws DomainException {
        if (openingHours == null) {
            throw new DomainException("OpeningHours can't be null.");
        }
        if(openingHours.equals("")) {
            this.openingHours = "There are no opening hours provided.";
        }
        this.openingHours = openingHours;
    }
}
