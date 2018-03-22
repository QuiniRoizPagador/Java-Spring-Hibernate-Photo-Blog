package es.blog.service;

import es.blog.dao.SkillDao;
import es.blog.model.Skill;
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
@Service("skillService")
@Transactional
public class SkillServiceImpl implements SkillService{
    
    @Autowired
    private SkillDao dao;

    @Override
    public List<Skill> listSkills() throws QueryException, SQLException {
        return dao.listSkills();
    }

    @Override
    public void addSkill(Skill skill) throws QueryException, SQLException {
        dao.addSkill(skill);
    }

    @Override
    public void deleteSkill(int id) throws QueryException, SQLException {
        dao.deleteSkill(id);
    }

    @Override
    public Skill getSkill(int id) throws QueryException, SQLException {
        return dao.getSkill(id);
    }

    @Override
    public void updateSkill(Skill skill) throws QueryException, SQLException {
        dao.updateSkill(skill);
    }
}
