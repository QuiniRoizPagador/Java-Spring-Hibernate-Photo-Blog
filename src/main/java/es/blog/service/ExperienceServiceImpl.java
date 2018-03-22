package es.blog.service;

import es.blog.dao.ExperienceDao;
import es.blog.model.Experience;
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
@Service("experienceService")
@Transactional
public class ExperienceServiceImpl implements ExperienceService{
    
    @Autowired
    private ExperienceDao dao;

    @Override
    public List<Experience> listExperience() throws QueryException, SQLException {
        return dao.listExperience();
    }

    @Override
    public void addExperience(Experience experience) throws QueryException, SQLException {
        dao.addExperience(experience);
    }

    @Override
    public void deleteExperience(int id) throws QueryException, SQLException {
        dao.deleteExperience(id);
    }

    @Override
    public Experience getExperience(int id) throws QueryException, SQLException {
        return dao.getExperience(id);
    }

    @Override
    public void updateExperience(Experience experience) throws QueryException, SQLException {
        dao.updateExperience(experience);
    }
    
}
