package by.snenn.dao.daoImpl;


import by.snenn.dao.ICreditCardDao;
import by.snenn.pojos.CreditCard;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CreditCardDao extends Dao<CreditCard> implements ICreditCardDao<CreditCard> {

    private static Logger logger = Logger.getLogger(CreditCardDao.class);

    @Autowired
    public CreditCardDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<CreditCard> getAll() {
        List<CreditCard> creditCards = null;
        try {
            Criteria cr = getSession().createCriteria(CreditCard.class);
            creditCards = cr.list();
            logger.info("all creditCards:" + creditCards);
        } catch (HibernateException e) {
            logger.error("Error get creditCards" + e);
        }
        return creditCards;
    }


    public List getAllByIdAccount(int id) {
        List creditCards = null;
        try {
            Query query = getSession().createQuery("FROM CreditCard WHERE account.id=:id ");
            query.setParameter("id", id);
            logger.info("123");
            creditCards = query.list();
            logger.info("get creditCards: " + creditCards);
        } catch (HibernateException e) {
            logger.error("Error get CreditCards" + e);
        }
        return creditCards;
    }


    public List<CreditCard> getAllLimit(int startNumber, int endNumber) {
        List<CreditCard> creditCards = null;
        try {
            Criteria cr = getSession().createCriteria(CreditCard.class);
            cr.setFirstResult(startNumber);
            cr.setMaxResults(endNumber);
            creditCards = cr.list();
        } catch (HibernateException e) {
            logger.error("Error get CreditCards" + e);
        }
        return creditCards;
    }

    @Override
    public List<CreditCard> getAllLimitByUser(int idUser) {
        List<CreditCard> creditCards = null;
        try {
            Query query = getSession().createQuery("FROM CreditCard WHERE account.user.id=:id ");
            query.setParameter("id", idUser);
            creditCards = query.list();
        } catch (HibernateException e) {
            logger.error("Error get CreditCards" + e);
        }
        return creditCards;
    }

    public int getCount() {
        int result = 0;
        try {
            Query query = getSession().createQuery("select count (*) FROM CreditCard");
            result = Integer.parseInt(String.valueOf(query.uniqueResult()));

        } catch (HibernateException e) {
            logger.error("Error get CreditCards" + e);
        }

        return result;
    }

    @Override
    public int getCountByUser(int id) {
        int result = 0;
        try {
            Query query = getSession().createQuery("select count (*) FROM CreditCard where account.user.id=:id");
            query.setParameter("id", id);
            result = Integer.parseInt(String.valueOf(query.uniqueResult()));

        } catch (HibernateException e) {
            logger.error("Error get CreditCards" + e);
        }

        return result;
    }

    public CreditCard getLastPost() {
        CreditCard creditCard = new CreditCard();
        try {
            Query query = getSession().createQuery("FROM CreditCard c ORDER BY c.id DESC LIMIT 1;");
            query.setMaxResults(1);
            creditCard = (CreditCard) query.uniqueResult();

        } catch (HibernateException e) {
            logger.error("Error get creditCard" + e);
        }
        return creditCard;
    }


}
