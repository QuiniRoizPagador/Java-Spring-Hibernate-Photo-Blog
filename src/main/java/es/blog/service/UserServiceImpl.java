package es.blog.service;

import es.blog.dao.UserDao;
import es.blog.model.ImageData;
import es.blog.model.SystemUser;
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
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDao dao;
     
    /**
     *
     * @param id
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public SystemUser find(int id)throws QueryException, SQLException  {
        return dao.find(id);
    }
    
    /**
     *
     * @param username
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public SystemUser find(String username) throws QueryException, SQLException {
        return dao.find(username);
    }

    /**
     *
     * @param user
     * @throws QueryException
     * @throws SQLException
     */
    @Override
    public void updatePassword(SystemUser user) throws QueryException, SQLException {
        dao.updatePassword(user);
    }

    /**
     *
     * @return
     * @throws QueryException
     * @throws SQLException
     */
    @Override
    public List<ImageData> getImagesAndFamiliesFromUser() throws QueryException, SQLException {
        return dao.getImagesAndFamiliesFromUser();
    }
}
