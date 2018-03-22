package es.blog.dao;

import es.blog.config.sql.AbstractDao;
import es.blog.model.Image;
import es.blog.model.Info;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Quini_Dev
 */
@Repository("infoDao")
public class InfoDaoImpl extends AbstractDao<Integer, Info> implements InfoDao {

    @Override
    public Info getInfo() throws QueryException, SQLException {
        Criteria queryCriteria = getSession().createCriteria(Info.class);
        queryCriteria.setFirstResult(0);
        queryCriteria.setMaxResults(1);
        List<Info> infos = queryCriteria.list();
        if (infos.isEmpty()) {
            Info info = new Info();
            info.setId(1);
            info.setUuid(UUID.randomUUID().toString());
            save(info);
            return getByKey(1);
        } else {
            return infos.get(0);
        }
    }

    @Override
    public void updateInfo(Info info) throws QueryException, SQLException {
        String stquery = "UPDATE Info SET name=:name, mail=:mail, city=:city, phone=:phone, description=:description, footer=:footer";
        if (info.getImage1() != null) {
            stquery += ", image1=:image1";
        }
        if (info.getImage2() != null) {
            stquery += ", image2=:image2";
        }
        Query query = getSession().createQuery(stquery + " WHERE uuid=:uuid");
        query.setParameter("name", info.getName());
        query.setParameter("mail", info.getMail());
        query.setParameter("city", info.getCity());
        query.setParameter("phone", info.getPhone());
        query.setParameter("description", info.getDescription());
        query.setParameter("footer", info.getFooter());
        query.setParameter("uuid", info.getUuid());
        if (info.getImage1() != null) {
            query.setParameter("image1", info.getImage1());
        }
        if (info.getImage2() != null) {
            query.setParameter("image2", info.getImage2());
        }
        query.executeUpdate();
    }

    @Override
    public Image getPhoto(String id, int imagen) throws QueryException, SQLException {
        Query query = getSession().createQuery("FROM Info WHERE uuid = :uuid");
        query.setParameter("uuid", id);
        Info res = (Info) query.uniqueResult();
        if (imagen == 1) {
            return res.getImage1();
        } else {
            return res.getImage2();
        }
    }

}
