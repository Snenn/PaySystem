package by.snenn.dao;

import java.util.List;


public interface IUserDao<TYPE> extends IDao<TYPE> {
    List<TYPE> getAll() ;

    public TYPE getLastPost();

    TYPE findByLogin(String login);

    List<TYPE> getAllLimit(int startNumber, int endNumber) ;

    int getCount();
}
