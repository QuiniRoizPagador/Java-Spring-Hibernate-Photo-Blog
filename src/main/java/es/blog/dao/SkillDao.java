package es.blog.dao;

import es.blog.model.Skill;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.QueryException;

/**
 *
 * @author Quini_Dev
 */
public interface SkillDao {

    List<Skill> listSkills() throws QueryException, SQLException;

    void addSkill(Skill skill) throws QueryException, SQLException;

    void deleteSkill(int id) throws QueryException, SQLException;

    Skill getSkill(int id) throws QueryException, SQLException;

    void updateSkill(Skill skill) throws QueryException, SQLException;
}
