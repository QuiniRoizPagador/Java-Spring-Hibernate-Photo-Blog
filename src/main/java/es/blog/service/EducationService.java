package es.blog.service;

import es.blog.model.Education;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface EducationService {

    List<Education> listEducation() throws QueryException, SQLException;

    void addEducation(Education education) throws QueryException, SQLException;

    void deleteEducation(int id) throws QueryException, SQLException;

    Education getEducation(int id) throws QueryException, SQLException;

    void updateEducation(Education education) throws QueryException, SQLException;
}
