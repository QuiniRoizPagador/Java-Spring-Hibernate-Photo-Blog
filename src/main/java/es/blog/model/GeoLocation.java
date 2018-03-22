package es.blog.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Quini_Dev
 */
@Entity
@Table(name = "geolocation")
public class GeoLocation implements Serializable{

    @Id
    private String sesionId;
    private String locationCountry;

    public GeoLocation() {

    }

    public String getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

}
