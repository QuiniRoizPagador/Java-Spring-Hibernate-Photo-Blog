package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.Skill;
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
@Repository("skillDao")
public class SkillDaoImpl extends AbstractDao<Integer, Skill> implements SkillDao {

    @Override
    @SuppressWarnings("unchecked")
    public List<Skill> listSkills() throws QueryException, SQLException {
        Criteria criteria = createEntityCriteria();
        return (List<Skill>) criteria.list();
    }

    @Override
    public void addSkill(Skill skill) throws QueryException, SQLException {
        save(skill);
    }

    @Override
    public void deleteSkill(int id) throws QueryException, SQLException {
        Query query = getSession().createQuery("delete from Skill where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Skill getSkill(int id) throws QueryException, SQLException {
        return getByKey(id);
    }

    @Override
    public void updateSkill(Skill skill) throws QueryException, SQLException {
        Query query = getSession().createQuery("UPDATE Skill SET isSkill=:isSkill, percent=:percent, description=:description WHERE id = :id");
        query.setParameter("isSkill", skill.isIsSkill());
        query.setParameter("percent", skill.getPercent());
        query.setParameter("description", skill.getDescription());
        query.setParameter("id", skill.getId());
        query.executeUpdate();
    }

}
