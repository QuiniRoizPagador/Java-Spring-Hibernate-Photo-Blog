package es.blog.service;

import es.blog.model.Image;
import es.blog.model.Photo;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface PhotoService {

    List<Photo> listPhotos() throws QueryException, SQLException;

    List<Photo> listPhotos(int family) throws QueryException, SQLException;

    void addPhoto(Photo photo) throws QueryException, SQLException;

    void deletePhoto(String uuid) throws QueryException, SQLException;

    void updatePhoto(Photo photo) throws QueryException, SQLException;

    Image getPhoto(String id) throws QueryException, SQLException;
}
