package by.snenn.services.daoImpl;


import by.snenn.dao.IAccountDao;
import by.snenn.dao.ICreditCardDao;
import by.snenn.dao.IPaymentDao;
import by.snenn.pojos.Account;
import by.snenn.pojos.CreditCard;
import by.snenn.pojos.Payment;
import by.snenn.services.IAccountService;
import by.snenn.services.IPaymentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    @Autowired
    private IPaymentDao paymentDao;
    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private ICreditCardDao creditCardDao;
    @Autowired
    private IAccountService accountService;

    private Logger logger = Logger.getLogger(CreditCardService.class.getName());
    private String messages = null;

    @Override
    public List getAllPayment() {
        List<Payment> payments = paymentDao.getAll();
        return payments;
    }

    @Override
    public String createPayment(int idCard, int sum, int idUser) {
        try {
            CreditCard card = (CreditCard) creditCardDao.get(idCard);
            if (card.getCreditCardStatus() == 1) {
                if (accountService.checkPermission(idUser, card.getAccount().getId())) {
                    Account account = (Account) accountDao.get(card.getAccount().getId());
                    if (account.getBalance() >= sum) {
                        Payment payment = new Payment();
                        payment.setSum(sum);
                        payment.setCreditCardPayment(card.getId());
                        payment.setData(Calendar.getInstance().getTime());
                        account.setBalance(account.getBalance() - sum);
                        accountDao.saveOrUpdate(account);
                        paymentDao.saveOrUpdate(payment);
                        messages = "payment save";
                    } else messages = "no money";
                } else messages = "error123";
            } else messages = "card is blocked";
        } catch (Exception e) {
            messages = "Payment error, please try again";
        }
        return messages;
    }

    @Override
    public List getPaymentsLimit(int startNumber, int countFields) {
        return (List<Payment>) paymentDao.getAllLimit(startNumber, countFields);
    }

    @Override
    public List getPaymentsLimitByUser(int idUser) {
        return (List<Payment>) paymentDao.getAllLimitByUser(idUser);
    }

    @Override
    public int getCountPayments() {
        return paymentDao.getCount();
    }
}
