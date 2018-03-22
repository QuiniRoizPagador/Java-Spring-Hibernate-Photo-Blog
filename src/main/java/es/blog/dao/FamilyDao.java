package es.blog.dao;

import es.blog.model.PhotoFamily;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface FamilyDao {

    List<PhotoFamily> listFamilies() throws QueryException, SQLException;

    void addFamily(PhotoFamily family) throws QueryException, SQLException;

    void deleteFamily(int id) throws QueryException, SQLException;

    PhotoFamily getFamily(int id) throws QueryException, SQLException;

    void updateFamily(PhotoFamily family) throws QueryException, SQLException;
}
