package by.snenn.dao.daoImpl;




import by.snenn.dao.IUserRoleDao;
import by.snenn.pojos.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDao extends Dao<UserRole> implements IUserRoleDao<UserRole> {

        private static Logger logger = Logger.getLogger(UserRoleDao.class);


    @Autowired
    public UserRoleDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public List getAll() {
        List userRoles = null;
        try {
            Query query = getSession().createQuery("FROM userrole ");
            userRoles = query.list();
            logger.info("all userRoles:" + userRoles);
        } catch (HibernateException e) {
            logger.error("Error get userRoles" + e);
        }
        return userRoles;
    }
}
