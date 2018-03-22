package es.blog.dao;

import es.blog.model.ImageData;
import es.blog.model.SystemUser;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface UserDao {

    SystemUser find(int id) throws QueryException, SQLException;

    SystemUser find(String username) throws QueryException, SQLException;

    void updatePassword(SystemUser user) throws QueryException, SQLException;

    List<ImageData> getImagesAndFamiliesFromUser() throws QueryException, SQLException;
}
