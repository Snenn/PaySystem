package by.snenn.dao;

import by.snenn.pojos.CreditCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@ContextConfiguration("/beans-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CreditCardDaoTest {

    @Autowired
    ICreditCardDao creditCardDao;

    @Test
    public void getAll() throws Exception {
        CreditCard card = new CreditCard(1, null, 1, new Date());
        creditCardDao.saveOrUpdate(card);
        List cards= creditCardDao.getAll();
        Assert.assertNotNull(cards);
    }

    @Test
    public void getAllByIdAccount() throws Exception {


    }

    @Test
    public void getAllLimit() throws Exception {

    }

    @Test
    public void getCount() throws Exception {

    }

    @Test
    public void getLastPost() throws Exception {

    }

    @Test
    public void saveOrUpdate() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void get() throws Exception {

    }




}