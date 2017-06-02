package ucll.da.placedomain.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Calendar;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 * Created by verme on 3/05/2017.
 */
@Named
@Entity
public class Place {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(unique = true)
    private String placeId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+2")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDetails placeDetails;

    public Place() {

    }

    public Place(String placeId) throws DomainException {
        this.setPlaceId(placeId);
        this.setDate(Calendar.getInstance());
    }

    public Place(String placeId, Calendar date) throws DomainException {
        this.setPlaceId(placeId);
        this.setDate(date);
    }

    public Place(String placeId, PlaceDetails placeDetails) throws DomainException {
        this.setPlaceId(placeId);
        this.setDate(Calendar.getInstance());
        this.setPlaceDetails(placeDetails);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) throws DomainException {
        if (placeId == null || placeId.equals("")) {
            throw new DomainException("The placeId can't be null or empty.");
        }
        this.placeId = placeId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) throws DomainException {
        if (date == null) {
            throw new DomainException("The date can't be null");
        }
        this.date = date;
    }

    public PlaceDetails getPlaceDetails() {
        return placeDetails;
    }

    public void setPlaceDetails(PlaceDetails placeDetails) throws DomainException {
        if (placeDetails == null) {
            throw new DomainException("The placeDetails can't be null");
        }
        this.placeDetails = placeDetails;
    }
}
