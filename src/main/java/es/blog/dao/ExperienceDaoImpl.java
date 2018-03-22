package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.Experience;
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
@Repository("experienceDao")
public class ExperienceDaoImpl extends AbstractDao<Integer, Experience> implements ExperienceDao {

    @Override
    public List<Experience> listExperience() throws QueryException, SQLException {
        Criteria criteria = createEntityCriteria();
        return (List<Experience>) criteria.list();
    }

    @Override
    public void addExperience(Experience experience) throws QueryException, SQLException {
        save(experience);
    }

    @Override
    public void deleteExperience(int id) throws QueryException, SQLException {
        Query query = getSession().createQuery("delete from Experience where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Experience getExperience(int id) throws QueryException, SQLException {
        return getByKey(id);
    }

    @Override
    public void updateExperience(Experience experience) throws QueryException, SQLException {
        Query query = getSession().createQuery("UPDATE Experience SET _from=:from, _to=:to, puesto=:puesto, empresa=:empresa, description=:description WHERE id = :id");
        query.setParameter("from", experience.getFrom());
        query.setParameter("to", experience.getTo());
        query.setParameter("puesto", experience.getPuesto());
        query.setParameter("empresa", experience.getEmpresa());
        query.setParameter("description", experience.getDescription());
        query.setParameter("id", experience.getId());
        query.executeUpdate();
    }

}
