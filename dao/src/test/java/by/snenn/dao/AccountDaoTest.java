package by.snenn.dao;

import by.snenn.pojos.Account;
import by.snenn.pojos.User;
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
public class AccountDaoTest {

    @Autowired
    IAccountDao accountDao;

    @Test
    public void getAll() throws Exception {
        User user = new User();
        Account account = new Account(100, user, null, new Date());
        accountDao.saveOrUpdate(account);
        List<Account> accounts = accountDao.getAll();
        Assert.assertNotNull(accounts);
    }

    @Test
    public void readByFKUser() throws Exception {

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