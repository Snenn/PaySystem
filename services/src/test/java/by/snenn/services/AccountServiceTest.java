package by.snenn.services;

import by.snenn.dao.daoImpl.AccountDao;
import by.snenn.pojos.Account;
import by.snenn.services.daoImpl.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnit44Runner;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration("/beans-services.xml")
@RunWith(MockitoJUnit44Runner.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountDao accountDao;

    @Test
    public void testResetAccountUser_withBalance0() throws Exception {
        Account account = mock(Account.class);
        when(accountDao.get(1)).thenReturn(account);
        when(account.getBalance()).thenReturn(0);
        Assert.assertEquals(accountService.resetAccountUser(1), "successful");
    }

    @Test
    public void testResetAccountUser_withBalanceNot0() throws Exception {
        Account account = mock(Account.class);
        when(accountDao.get(1)).thenReturn(account);
        when(account.getBalance()).thenReturn(1);
        Assert.assertEquals(accountService.resetAccountUser(1), "the account has money");
    }

    @Test
    public void viewAccountForAccount() throws Exception {

    }

    @Test
    public void viewCreditCardsForAccount() throws Exception {
    }

    @Test
    public void viewCreditCardStatusesForAccount() throws Exception {
    }

    @Test
    public void createAccount() throws Exception {
    }

}