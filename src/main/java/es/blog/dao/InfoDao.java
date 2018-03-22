package es.blog.dao;

import es.blog.model.Image;
import es.blog.model.Info;
import java.sql.SQLException;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface InfoDao {

    Info getInfo() throws QueryException, SQLException;

    void updateInfo(Info info) throws QueryException, SQLException;

    Image getPhoto(String id, int imagen) throws QueryException, SQLException;
}
