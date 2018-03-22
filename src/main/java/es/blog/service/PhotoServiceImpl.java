package es.blog.service;

import es.blog.dao.PhotoDao;
import es.blog.model.Image;
import es.blog.model.Photo;
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
@Service("photoService")
@Transactional
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoDao dao;

    @Override
    public List<Photo> listPhotos() throws QueryException, SQLException {
        return dao.listPhotos();
    }

    @Override
    public void addPhoto(Photo photo) throws QueryException, SQLException {
        dao.addPhoto(photo);
    }

    @Override
    public void deletePhoto(String uuid) throws QueryException, SQLException {
        dao.deletePhoto(uuid);
    }

    @Override
    public void updatePhoto(Photo photo) throws QueryException, SQLException {
        dao.updatePhoto(photo);
    }

    @Override
    public List<Photo> listPhotos(int idFamily) throws QueryException, SQLException {
        return dao.listPhotos(idFamily);
    }

    @Override
    public Image getPhoto(String id) throws QueryException, SQLException {
        return dao.getPhoto(id);
    }
}
