package es.blog.service;

import es.blog.dao.FamilyDao;
import es.blog.model.Image;
import es.blog.model.PhotoFamily;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Quini_Dev
 */
@Service("familyService")
@Transactional
public class FamilyServiceImpl implements FamilyService {

    @Autowired
    private FamilyDao dao;

    @Override
    public List<PhotoFamily> listFamilies() throws QueryException, SQLException {
        return dao.listFamilies();
    }

    @Override
    public void addFamily(PhotoFamily family) throws QueryException, SQLException {
        dao.addFamily(family);
    }

    @Override
    public void deleteFamily(int id) throws QueryException, SQLException {
        dao.deleteFamily(id);
    }

    @Override
    public PhotoFamily getFamily(int id) throws QueryException, SQLException {
        return dao.getFamily(id);
    }

    @Override
    public void updateFamily(PhotoFamily family) throws QueryException, SQLException {
        dao.updateFamily(family);
    }

    @Override
    public Image getFamilyPortade(int id) throws QueryException, SQLException {
        return dao.getFamily(id).getFirstImage();
    }

}
