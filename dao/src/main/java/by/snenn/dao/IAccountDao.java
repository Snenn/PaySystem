package by.snenn.dao;

import by.snenn.pojos.Account;

import java.util.List;

public interface IAccountDao<TYPE> extends IDao<TYPE> {

    List<TYPE> getAll();

    List<Account> getByIdUser(int id);

    TYPE getLastPost() ;

    List<Account> getAllLimit(int startNumber, int countFields);

    List<Account> getAllByUser(int id);

    int getCountAll();

    int getCountByUser(int id);

    int sumAllBalance ();

    int sumAllBalanceByUser (int id);
}
