package es.blog.service;

import es.blog.model.GeoLocation;
import java.sql.SQLException;
import java.util.Map;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface GeoLocationService {

    GeoLocation find(String id) throws QueryException, SQLException;

    void save(GeoLocation geoLocation) throws QueryException, SQLException;

    Map<String, String> findAll() throws QueryException, SQLException;

    long countAll() throws QueryException, SQLException;
}
