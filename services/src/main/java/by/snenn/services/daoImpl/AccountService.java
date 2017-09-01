package by.snenn.services.daoImpl;


import by.snenn.dao.*;
import by.snenn.pojos.Account;
import by.snenn.pojos.User;
import by.snenn.services.IAccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {

    private Logger logger = Logger.getLogger(AccountService.class.getName());
    private String messages = null;


    @Autowired
    private ICreditCardDao creditCardDao;
    @Autowired
    private ICreditCardStatusDao creditCardStatusDao;
    @Autowired
    private IAccountDao accountDao;

    @Override
    public String resetAccountUser(int idAccount) {
        try {
            Account account = (Account) accountDao.get(idAccount);
            if (account.getBalance() == 0) {
                accountDao.delete(account);
                messages = "successful";
            } else messages = "the account has money";
        } catch (Exception e) {
            logger.error(e);
        }
        return messages;
    }


    @Override
    public Account viewAccountForAccount(int id) {
        return (Account) accountDao.getByIdUser(id);
    }

    @Override
    public List viewCreditCardsForAccount(int id) {
        return creditCardDao.getAllByIdAccount(id);
    }

    @Override
    public List viewCreditCardStatusesForAccount() {
        return creditCardStatusDao.getAll();
    }

    @Override
    public String createAccount(User user) {

        try {
            if (accountDao.getCountByUser(user.getId()) < 5) {
                Account account = new Account(0, user, null, new Date());
                accountDao.saveOrUpdate(account);
                messages = "account is created";
            } else messages = "you have 5 account";
        } catch (Exception e) {
            messages = "Error";
            logger.error("Error2");
        }
        return messages;
    }

    @Override
    public String putMoney(int idAccount, int sum, int idUser) {

        try {
            if (checkPermission(idUser, idAccount)) {
                Account account = (Account) accountDao.get(idAccount);
                account.setBalance(sum + account.getBalance());
                accountDao.saveOrUpdate(account);
                messages = "successful!";
            } else messages = "error";
        } catch (Exception e) {
            logger.error("Error3");
            messages = "error";
        }


        return messages;
    }

    @Override
    public List getAccountsLimit(int startNumber, int countFields) {
        return (List<Account>) accountDao.getAllLimit(startNumber, countFields);
    }

    @Override
    public List getAccountsByUser(int idUser) {
        return (List<Account>) accountDao.getAllByUser(idUser);
    }

    @Override
    public boolean checkPermission(int idUser, int idAccount) {
        boolean check = false;
        try {

            List<Account> accounts = accountDao.getAllByUser(idUser);
            for (Account account : accounts) {
                if (account.getId() == idAccount) {
                    check = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return check;
    }

    @Override
    public int getCountAccounts() {
        return accountDao.getCountAll();
    }

    @Override
    public int getCountByUser(int id) {
        return accountDao.getCountByUser(id);
    }

    @Override
    public int getSumAllBalance() {
        return accountDao.sumAllBalance();
    }

    @Override
    public int getSumAllBalanceByUser(int id) {
        return accountDao.sumAllBalanceByUser(id);
    }
}


/*TODO: ERROR get ID for delete*/