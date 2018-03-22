package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.Image;
import java.sql.SQLException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quini_Dev
 */
@Repository("imageDao")
public class ImageDaoImpl extends AbstractDao<Integer, Image> implements ImageDao{

    @Override
    public void removeImage(Image image) throws SQLException, SQLGrammarException{
        Query query = getSession().createQuery("DELETE FROM Image WHERE id = :id");
        query.setInteger("id", image.getId());
        query.executeUpdate();
    }

    @Override
    public void saveImage(Image img) {
        save(img);
    }

    @Override
    public Image getImage(int id) throws QueryException, SQLException, SQLGrammarException {
        Query query = getSession().createQuery("FROM Image WHERE id = :id");
        query.setInteger("id", id);
        return (Image) query.uniqueResult();
    }
    
}
