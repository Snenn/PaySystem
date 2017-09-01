package by.snenn.services;


import by.snenn.pojos.User;
import by.snenn.pojos.UserRole;

import java.util.List;

public interface IUserService {

    void saveOrUpdate(User user);

    UserRole getRoleUser () throws Exception;

    User findByLogin(String login);

    List getUsersLimit(int startNumber, int countFields);

    int getCountUsers();

}
