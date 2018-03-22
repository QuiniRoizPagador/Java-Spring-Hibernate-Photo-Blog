package es.blog.dao;

import es.blog.model.Image;
import es.blog.model.Photo;
import es.blog.model.PhotoFamily;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface PhotoDao {

    List<Photo> listPhotos() throws QueryException, SQLException;

    void addPhoto(Photo photo) throws QueryException, SQLException;

    void deletePhoto(String uuid) throws QueryException, SQLException;

    Photo findById(int id) throws QueryException, SQLException;

    void updatePhoto(Photo photo) throws QueryException, SQLException;

    List<Photo> listPhotos(PhotoFamily family) throws QueryException, SQLException;

    List<Photo> listPhotos(int idFamily) throws QueryException, SQLException;

    Image getPhoto(String id) throws QueryException, SQLException;
}
