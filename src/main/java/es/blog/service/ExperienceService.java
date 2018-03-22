package es.blog.service;

import es.blog.model.Experience;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface ExperienceService {

    List<Experience> listExperience() throws QueryException, SQLException;

    void addExperience(Experience experience) throws QueryException, SQLException;

    void deleteExperience(int id) throws QueryException, SQLException;

    Experience getExperience(int id) throws QueryException, SQLException;

    void updateExperience(Experience experience) throws QueryException, SQLException;
}
