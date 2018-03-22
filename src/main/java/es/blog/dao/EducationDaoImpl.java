package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.Education;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quini_Dev
 */
@Repository("educationDao")
public class EducationDaoImpl extends AbstractDao<Integer, Education> implements EducationDao {

    @Override
    public List<Education> listEducation() throws QueryException, SQLException {
        Criteria criteria = createEntityCriteria();
        return (List<Education>) criteria.list();
    }

    @Override
    public void addEducation(Education education) throws QueryException, SQLException {
        save(education);
    }

    @Override
    public void deleteEducation(int id) throws QueryException, SQLException {
        Query query = getSession().createQuery("delete from Education where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Education getEducation(int id) throws QueryException, SQLException {
        return getByKey(id);
    }

    @Override
    public void updateEducation(Education education) throws QueryException, SQLException {
        Query query = getSession().createQuery("UPDATE Education SET _from=:from, _to=:to, place=:place, description=:description WHERE id = :id");
        query.setParameter("from", education.getFrom());
        query.setParameter("to", education.getTo());
        query.setParameter("place", education.getPlace());
        query.setParameter("description", education.getDescription());
        query.setParameter("id", education.getId());
        query.executeUpdate();
    }

}
