package by.snenn.dao.daoImpl;


import by.snenn.dao.IUserDao;
import by.snenn.pojos.CreditCard;
import by.snenn.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends Dao<User> implements IUserDao<User> {

    private static Logger logger = Logger.getLogger(UserDao.class);

    @Autowired
    public UserDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public List<User> getAll() {
        List users = null;
        try {
            Query query = getSession().createQuery("FROM User ");
            users = query.list();
            logger.info("all users:" + users);
        } catch (HibernateException e) {
            logger.error("Error get users" + e);
                    }
        return users;
    }

    public List<User> getAllLimit(int startNumber, int endNumber) {
        List<User> users = null;
        try {
            Criteria cr = getSession().createCriteria(User.class);
            cr.setFirstResult(startNumber);
            cr.setMaxResults(endNumber);
            users = cr.list();
        } catch (HibernateException e) {
            logger.error("Error get Users" + e);
        }
        return  users;
    }


    public User getLastPost() {
        User user = new User();
        try {
            Query query = getSession().createQuery("FROM User u ORDER BY u.id DESC");
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Error get user" + e);
        }
        return user;

    }

    public User findByLogin(String login) {
        User user = new User();
        try {
            Query query = getSession().createQuery("from User u where u.login = :login");
            query.setParameter("login",login);
            user = (User) query.uniqueResult();
            logger.info("get user" + user);
        } catch (HibernateException e) {
            logger.error("Error get user" + e);
        }
        return user;
    }

    public int getCount(){
        int result=0;
        try {
            Query query = getSession().createQuery("select count (*) FROM User");
            result = Integer.parseInt(String.valueOf(query.uniqueResult()));

        } catch (HibernateException e) {
            logger.error("Error get count User" + e);
        }

        return result;
    }


}
