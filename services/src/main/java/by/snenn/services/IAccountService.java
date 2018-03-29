package by.snenn.services;


import by.snenn.pojos.Account;
import by.snenn.pojos.User;

import java.util.List;

public interface IAccountService {

    String resetAccountUser(int idAccount);

    Account viewAccountForAccount(int id);

    List viewCreditCardsForAccount(int id);

    List viewCreditCardStatusesForAccount();

    String createAccount(User user);

    String putMoney(int idAccount, int sum, int idUser);

    List getAccountsLimit(int startNumber, int countFields);

    List getAccountsByUser(int idUser);

    boolean checkPermission (int idUser, int idAccount) throws Exception;

    int getCountAccounts();

    int getCountByUser(int id);

    int getSumAllBalance();

    int getSumAllBalanceByUser(int id);
}
