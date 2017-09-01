package by.snenn.dao.daoImpl;

import by.snenn.dao.IDao;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository
public class Dao<Type> implements IDao<Type> {

    private static Logger log = Logger.getLogger(Dao.class);
    private SessionFactory sessionFactory;

    @Autowired
    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Dao() {

    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(Type type) throws Exception {
        try {
            getSession().saveOrUpdate(type);
            log.info("Save or update (commit):" + type);
        } catch (HibernateException e) {
            log.error("Error save or update Object in IDao" + e);
            throw new Exception(e);
        }
    }

    @Override
    public void delete(Type type) throws Exception {
        try {
            getSession().delete(type);
            log.info("Delete:" + type);
        } catch (HibernateException e) {
            log.error("Error save or update PERSON in IDao" + e);
            throw new Exception(e);
        }
    }

    @Override
    public Type get(Serializable id) throws Exception {
        Type type;
        try {
            type = getSession().get(getPersistentClass(), id);
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in IDao" + e);
            throw new Exception(e);
        }
        return type;
    }
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(getPersistentClass());
    }
    private Class<Type> getPersistentClass() {
        return (Class<Type>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    }
