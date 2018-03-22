package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.ImageData;
import es.blog.model.PhotoFamily;
import es.blog.model.SystemUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.QueryException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quini_Dev
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, SystemUser> implements UserDao {
    
    @Autowired
    private FamilyDao familyDao;
    @Autowired
    private PhotoDao photoDao;

    /**
     *
     * @param id
     * @return
     * @throws QueryException
     * @throws SQLException
     */
    @Override
    public SystemUser find(int id) throws QueryException, SQLException {
        return getByKey(id);
    }

    /**
     *
     * @param username
     * @return
     * @throws QueryException
     * @throws SQLException
     */
    @Override
    public SystemUser find(String username) throws QueryException, SQLException {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username", username));
        return (SystemUser) criteria.uniqueResult();
    }

    @Override
    public void updatePassword(SystemUser user) throws QueryException, SQLException {
        getSession().update(user);
    }

    @Override
    public List<ImageData> getImagesAndFamiliesFromUser() throws QueryException, SQLException {
        ImageData img;
        List<ImageData> list = new ArrayList<>();
        List<PhotoFamily> families = familyDao.listFamilies();
        for (PhotoFamily family : families) {
         img = new ImageData();
         img.setFamily(family);
         img.setImages(photoDao.listPhotos(family));
         list.add(img);
        }
        return list;
    }

}
