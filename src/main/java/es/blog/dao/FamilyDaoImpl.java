package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.PhotoFamily;
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
@Repository("familyDao")
public class FamilyDaoImpl extends AbstractDao<Integer, PhotoFamily> implements FamilyDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<PhotoFamily> listFamilies() throws QueryException, SQLException{
        Criteria criteria = createEntityCriteria();
        return (List<PhotoFamily>) criteria.list();
    }

    @Override
    public void addFamily(PhotoFamily family) {
        save(family);
    }

    @Override
    public void deleteFamily(int id) throws QueryException, SQLException{
        Query query = getSession().createQuery("delete from PhotoFamily where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public PhotoFamily getFamily(int id) throws QueryException, SQLException{
        return getByKey(id);
    }

    @Override
    public void updateFamily(PhotoFamily family) throws QueryException, SQLException{
        Query query = getSession().createQuery("UPDATE PhotoFamily SET description=:description, firstImage=:firstImage WHERE id = :id");
        query.setParameter("description", family.getDescription().toUpperCase());
        query.setParameter("id", family.getId());
        query.setParameter("firstImage", family.getFirstImage());
        query.executeUpdate();
    }

}
