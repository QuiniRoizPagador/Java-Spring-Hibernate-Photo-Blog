package es.blog.service;

import es.blog.dao.GeoLocationDao;
import es.blog.model.GeoLocation;
import java.sql.SQLException;
import java.util.Map;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Quini_Dev
 */
@Service("geoLocationService")
@Transactional
public class GeoLocationServiceImpl implements GeoLocationService{
    @Autowired
    private GeoLocationDao dao;

    @Override
    public GeoLocation find(String id) throws QueryException, SQLException {
        return dao.find(id);
    }

    @Override
    public void save(GeoLocation geoLocation) throws QueryException, SQLException {
        dao.save(geoLocation);
    }

    @Override
    public Map<String, String> findAll() throws QueryException, SQLException {
        return dao.findAll();
    }

    @Override
    public long countAll() throws QueryException, SQLException {
        return dao.countAll();
    }
}
