package es.blog.service;

import es.blog.model.Image;
import java.sql.SQLException;
import org.hibernate.QueryException;
import org.hibernate.exception.SQLGrammarException;

/**
 *
 * @author Quini_Dev
 */
public interface ImageService {

    void removeImage(Image image1) throws SQLException, SQLGrammarException;

    void saveImage(Image img);

    public Image getImage(int id)throws SQLException, QueryException;

}
