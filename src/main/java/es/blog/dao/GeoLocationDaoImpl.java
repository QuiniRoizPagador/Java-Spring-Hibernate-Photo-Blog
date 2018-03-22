package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.GeoLocation;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quini_Dev
 */
@Repository("geoLocationDao")
public class GeoLocationDaoImpl extends AbstractDao<Integer, GeoLocation> implements GeoLocationDao {

    @Override
    public GeoLocation find(String id) {
        Query query = getSession().createQuery("FROM GeoLocation WHERE sesionId = :id");
        query.setParameter("id", id);
        return (GeoLocation) query.uniqueResult();
    }

    @Override
    public Map<String, String> findAll() {

        Query query = getSession().createQuery("SELECT locationCountry, COUNT(*) from GeoLocation GROUP BY locationCountry");
        List<Object[]> list = query.list();
        HashMap<String, String> map = new HashMap<>();
        list.forEach((object) -> {
            map.put(object[1].toString(), object[0].toString());
        });
        
        return map;
    }

    @Override
    public long countAll() throws QueryException, SQLException {
        Criteria crit = getSession().createCriteria(GeoLocation.class);
        crit.setProjection(Projections.rowCount());
        return (long) crit.uniqueResult();
    }
}
