package es.blog.service;

import es.blog.dao.ImageDao;
import es.blog.model.Image;
import java.sql.SQLException;
import org.hibernate.QueryException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Quini_Dev
 */
@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao dao;

    @Override
    public void removeImage(Image image) throws SQLException, SQLGrammarException {
        dao.removeImage(image);
    }

    @Override
    public void saveImage(Image img) {
        dao.saveImage(img);
    }

    @Override
    public Image getImage(int id) throws SQLException, QueryException {
        return dao.getImage(id);
    }

}
