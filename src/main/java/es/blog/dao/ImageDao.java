package es.blog.dao;

import es.blog.model.Image;
import java.sql.SQLException;
import org.hibernate.QueryException;
import org.hibernate.exception.SQLGrammarException;

/**
 *
 * @author Quini_Dev
 */
public interface ImageDao {

    void removeImage(Image image) throws SQLException, SQLGrammarException;

    void saveImage(Image img);

    Image getImage(int id) throws QueryException, SQLException, SQLGrammarException;

}
