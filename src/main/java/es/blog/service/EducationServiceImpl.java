package es.blog.service;

import es.blog.dao.EducationDao;
import es.blog.model.Education;
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
@Service("educationService")
@Transactional
public class EducationServiceImpl implements EducationService{
    @Autowired
    private EducationDao dao;

    @Override
    public List<Education> listEducation() throws QueryException, SQLException {
        return dao.listEducation();
    }

    @Override
    public void addEducation(Education education) throws QueryException, SQLException {
        dao.addEducation(education);
    }

    @Override
    public void deleteEducation(int id) throws QueryException, SQLException {
        dao.deleteEducation(id);
    }

    @Override
    public Education getEducation(int id) throws QueryException, SQLException {
        return dao.getEducation(id);
    }

    @Override
    public void updateEducation(Education education) throws QueryException, SQLException {
        dao.updateEducation(education);
    }
    
    
}
