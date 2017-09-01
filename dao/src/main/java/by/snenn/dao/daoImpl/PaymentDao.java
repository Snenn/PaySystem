package by.snenn.dao.daoImpl;

import by.snenn.dao.IPaymentDao;
import by.snenn.pojos.Payment;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDao extends Dao implements IPaymentDao {

    private static Logger logger = Logger.getLogger(AccountDao.class);

    @Autowired
    public PaymentDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Payment> getAll() {
        List payments = null;
        try {
            Query query = getSession().createQuery("FROM Payment");
            payments = query.list();
            logger.info("all users:" + payments);
        } catch (HibernateException e) {
            logger.error("Error get users" + e);
        }
        return payments;
    }

    @Override
    public List<Payment> getAllLimit(int startNumber, int countFields) {
        List<Payment> payments = null;
        try {
            Criteria cr = getSession().createCriteria(Payment.class);
            cr.setFirstResult(startNumber);
            cr.setMaxResults(countFields);
            payments = cr.list();
        } catch (HibernateException e) {
            logger.error("Error get Payments" + e);
        }
        return payments;
    }

    @Override
    public List<Payment> getAllLimitByUser(int idUser) {
        List<Payment> payments = null;
        try {
            Query query = getSession().createQuery("from Payment where creditCardPayment=:id");
            query.setParameter("id", idUser);
            payments = query.list();
        } catch (HibernateException e) {
            logger.error("Error get Payments" + e);
        }
        return payments;
    }

    @Override
    public int getCount() {
        int result = 0;
        try {
            Query query = getSession().createQuery("select count (*) FROM Payment");
            result = Integer.parseInt(String.valueOf(query.uniqueResult()));

        } catch (HibernateException e) {
            logger.error("Error get count Payment" + e);
        }

        return result;
    }


}
