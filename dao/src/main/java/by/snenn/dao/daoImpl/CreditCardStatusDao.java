package by.snenn.dao.daoImpl;



import by.snenn.dao.ICreditCardStatusDao;
import by.snenn.pojos.CreditCardStatus;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CreditCardStatusDao extends Dao<CreditCardStatus> implements ICreditCardStatusDao<CreditCardStatus> {

    private static Logger logger = Logger.getLogger(CreditCardStatusDao.class);

    @Autowired
    public CreditCardStatusDao(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public List getAll() {
        List  creditCardStatuses= null;
        try {
            Query query = getSession().createQuery("FROM CreditCardStatus ");
            creditCardStatuses = query.list();
            logger.info("all orders:" + creditCardStatuses);
        } catch (HibernateException e) {
            logger.error("Error get creditCardStatuses" + e);
        }
        return creditCardStatuses;
    }
}
