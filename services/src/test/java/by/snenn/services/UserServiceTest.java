package by.snenn.services;

import by.snenn.pojos.User;
import by.snenn.pojos.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/beans-services.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(true)
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    public void saveOrUpdate() throws Exception {
        User user= new User("UserServiceTest","UserServiceTest","UserServiceTest","UserServiceTest",null,null);
        userService.saveOrUpdate(user);
        User user1=userService.findByLogin("UserServiceTest");
        Assert.assertEquals(user, user1);

    }

    @Test
    public void getRoleUser() throws Exception {
        UserRole userRole=userService.getRoleUser();
        Assert.assertEquals(userRole.getIdRoleUser(), 1);

    }

    @Test
    public void findByLogin() throws Exception {
        User user= new User("UserServiceTest","UserServiceTest","UserServiceTest","UserServiceTest",null,null);
        userService.saveOrUpdate(user);
        User user1=userService.findByLogin("UserServiceTest");
        Assert.assertEquals(user, user1);
    }

}