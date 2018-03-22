package es.blog.service;

import es.blog.dao.InfoDao;
import es.blog.model.Image;
import es.blog.model.Info;
import java.sql.SQLException;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Quini_Dev
 */
@Service("infoService")
@Transactional
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoDao dao;

    @Override
    public Info getInfo() throws QueryException, SQLException {
        return dao.getInfo();
    }

    @Override
    public void updateInfo(Info info) throws QueryException, SQLException {
        dao.updateInfo(info);
    }

    @Override
    public Image getPhoto(String id, int imagen) throws QueryException, SQLException {
        return dao.getPhoto(id, imagen);
    }

}
