package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.Image;
import es.blog.model.Photo;
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
@Repository("photoDao")
public class PhotoDaoImpl extends AbstractDao<Integer, Photo> implements PhotoDao {

    @Override
    public Photo findById(int id) throws QueryException, SQLException {
        return getByKey(id);
    }

    @Override
    public List<Photo> listPhotos() throws QueryException, SQLException {
        Criteria criteria = createEntityCriteria();
        return (List<Photo>) criteria.list();
    }

    @Override
    public void addPhoto(Photo photo) throws QueryException, SQLException {
        save(photo);
    }

    @Override
    public void deletePhoto(String uuid) throws QueryException, SQLException {
        Query query = getSession().createQuery("DELETE FROM Photo WHERE uuid = :uuid");
        query.setString("uuid", uuid);
        query.executeUpdate();
    }

    @Override
    public void updatePhoto(Photo photo) throws QueryException, SQLException {
        String stquery = "UPDATE Photo SET title=:title, description=:description, family_id=:family";
        if (photo.getImage() != null) {
            stquery += ", _images=:image";
        }
        Query query = getSession().createQuery(stquery + " WHERE uuid = :uuid");
        query.setParameter("title", photo.getTitle());
        query.setParameter("description", photo.getDescription());
        query.setParameter("family", photo.getFamily().getId());
        query.setParameter("uuid", photo.getUuid());
        if (photo.getImage() != null) {
            query.setParameter("image", photo.getImage());
        }
        query.executeUpdate();
    }

    @Override
    public List<Photo> listPhotos(PhotoFamily family)  throws QueryException, SQLException{
        return listPhotos(family.getId());
    }

    @Override
    public List<Photo> listPhotos(int idFamily)  throws QueryException, SQLException{
        Query query = getSession().createQuery("FROM Photo WHERE family_id = :family_id");
        query.setInteger("family_id", idFamily);
        return query.list();
    }

    @Override
    public Image getPhoto(String id) throws QueryException, SQLException {
        Query query = getSession().createQuery("FROM Photo WHERE uuid = :uuid");
        query.setParameter("uuid", id);
        Photo res = (Photo) query.uniqueResult();
        return res.getImage();
    }

}
