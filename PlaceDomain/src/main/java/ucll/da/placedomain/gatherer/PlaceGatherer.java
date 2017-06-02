package ucll.da.placedomain.gatherer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ucll.da.placedomain.domain.DomainException;

/**
 * Created by verme on 7/05/2017.
 */
@Named
@RequestScoped
@Default
public class PlaceGatherer {

    public String getNameFromGooglePlace(String placeId) throws DomainException {
        try {
            return this.getGooglePlaceDetails(placeId).getString("name");
        } catch (JSONException e) {
            throw new DomainException("Couldn't parse JSON: name." + e.getMessage());
        }
    }

    public double getLongitudeFromGooglePlace(String placeId) throws DomainException {
        try {
            JSONObject jsonObject = this.getGooglePlaceDetails(placeId).getJSONObject("geometry");
            JSONObject latLng = jsonObject.getJSONObject("location");
            return latLng.getDouble("lng");
        } catch (JSONException e) {
            throw new DomainException("Couldn't parse JSON: lng." + e.getMessage());
        }

    }

    public double getLatitudeFromGooglePlace(String placeId) throws DomainException {
        try {
            JSONObject jsonObject = (JSONObject) this.getGooglePlaceDetails(placeId).getJSONObject("geometry");
            JSONObject latLng = (JSONObject) jsonObject.getJSONObject("location");
            return latLng.getDouble("lat");
        } catch (JSONException e) {
            throw new DomainException("Couldn't parse JSON: lat." + e.getMessage());
        }
    }

    public String getAddressFromGooglePlace(String placeId) throws DomainException {
        try {
            return this.getGooglePlaceDetails(placeId).getString("formatted_address");
        } catch (JSONException e) {
            throw new DomainException("Couldn't parse JSON: formatted_address." + e.getMessage());
        }
    }

    public double getRatingFromGooglePlace(String placeId) throws DomainException {
        try {
            return this.getGooglePlaceDetails(placeId).getDouble("rating");
        } catch (JSONException e) {
            throw new DomainException("Couldn't parse JSON: rating." + e.getMessage());
        }
    }

    public String getOpeningHoursFromGooglePlace(String placeId) throws DomainException {
        try {
            String openingHours = "";
            if (this.getGooglePlaceDetails(placeId).has("opening_hours")) {
                JSONObject jsonObject = this.getGooglePlaceDetails(placeId).getJSONObject("opening_hours");
                JSONArray arr = jsonObject.getJSONArray("weekday_text");
                openingHours = "\t";
                for (int i = 0; i < arr.length(); i++) {
                    openingHours += arr.getString(i) + "\n\t";
                }
            }
            return openingHours;
        } catch (JSONException e) {
            throw new DomainException("Couldn't parse JSON: opening_hours." + e.getMessage());
        }

    }

    private JSONObject getGooglePlaceDetails(String placeId) throws DomainException {
        try {
            String charset = StandardCharsets.UTF_8.name();
            URI uri = new URI("https",
                    "maps.googleapis.com",
                    "/maps/api/place/details/json",
                    "placeid=" + placeId + "&key=AIzaSyD2kHFGXbHmzP0Jqql0JXoTznGHDmvx9qU",
                    null);
            URLConnection connection = new URL(uri.toASCIIString()).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            Scanner scanner = new Scanner(response);
            String details = scanner.useDelimiter("\\A").next();
            JSONObject jsonObject = new JSONObject(details);
            if (jsonObject.has("result")) {
                return jsonObject.getJSONObject("result");
            } else {
                throw new JSONException("Couldn't parse JSON: result.");
            }
        } catch (IOException | URISyntaxException | JSONException e) {
            throw new DomainException(e.getMessage());
        }
    }
}
