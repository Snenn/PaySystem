package by.snenn.dao.daoImpl;

import by.snenn.dao.ITransferDao;
import by.snenn.pojos.Transfer;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferDao extends Dao implements ITransferDao {

    private static Logger logger = Logger.getLogger(AccountDao.class);

    @Autowired
    public TransferDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Transfer> getAll() {
        List transfers = null;
        try {
            Query query = getSession().createQuery("FROM Transfer ");
            transfers = query.list();
            logger.info("all users:" + transfers);
        } catch (HibernateException e) {
            logger.error("Error get users" + e);
        }
        return transfers;
    }

    @Override
    public List<Transfer> getAllLimit(int startNumber, int countFields) {
        List<Transfer> transfers = null;
        try {
            Criteria cr = getSession().createCriteria(Transfer.class);
            cr.setFirstResult(startNumber);
            cr.setMaxResults(countFields);
            transfers = cr.list();
        } catch (HibernateException e) {
            logger.error("Error get Transfers" + e);
        }
        return transfers;
    }

    @Override
    public List<Transfer> getAllLimitByUser(int idUser) {
        List<Transfer> transfers = null;
        try {
            Query query = getSession().createQuery("from Transfer where cardSender=:id");
            query.setParameter("id", idUser);
            transfers = query.list();
        } catch (HibernateException e) {
            logger.error("Error get Transfers" + e);
        }
        return transfers;
    }

    @Override
    public int getCount() {
        int result = 0;
        try {
            Query query = getSession().createQuery("select count (*) FROM Transfer");
            result = Integer.parseInt(String.valueOf(query.uniqueResult()));

        } catch (HibernateException e) {
            logger.error("Error get count Transfer" + e);
        }

        return result;
    }
}
