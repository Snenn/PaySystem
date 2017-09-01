package by.snenn.services.daoImpl;


import by.snenn.dao.IUserDao;
import by.snenn.dao.IUserRoleDao;
import by.snenn.pojos.User;
import by.snenn.pojos.UserRole;
import by.snenn.services.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private Logger logger= Logger.getLogger(UserService.class.getName());
    private String messages;
    private User user;

    @Autowired
    IUserDao userDao;
    @Autowired
    IUserRoleDao userRoleDao;

    @Override
    public void saveOrUpdate(User user) {
        try {
            userDao.saveOrUpdate(user);
        } catch (Exception e) {
            logger.error("Error saveOrUpdate in UserService: " + e);
        }
    }

    @Override
    public UserRole getRoleUser() throws Exception {
        return (UserRole) userRoleDao.get(1);
    }


    @Override
    public User findByLogin(String login) {
        return (User) userDao.findByLogin(login);
    }

    @Override
    public List getUsersLimit(int startNumber, int countFields) {
        return (List<User>) userDao.getAllLimit(startNumber, countFields);
    }

    @Override
    public int getCountUsers() {
        return userDao.getCount();
    }


}
