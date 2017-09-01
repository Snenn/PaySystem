package by.snenn.services.daoImpl;


import by.snenn.dao.IAccountDao;
import by.snenn.dao.ICreditCardDao;
import by.snenn.dao.ICreditCardStatusDao;
import by.snenn.pojos.Account;
import by.snenn.pojos.CreditCard;
import by.snenn.pojos.User;
import by.snenn.services.ICreditCardService;
import by.snenn.services.IPaymentService;
import by.snenn.services.ITransferService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CreditCardService implements ICreditCardService {

    private Logger logger = Logger.getLogger(CreditCardService.class.getName());
    private String messages = null;
    private CreditCard creditCard = null;
    private int result = 0;

    @Autowired
    private IAccountDao accountDao;
    @Autowired
    private ICreditCardDao creditCardDao;
    @Autowired
    private ICreditCardStatusDao creditCardStatusDao;
    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private ITransferService transferService;


    @Override
    public String blockCardUser(User user, int id) {

        if (verifyCardByUser(user, id) >= 0) {
            CreditCard creditCard;
            try {
                logger.info("transction start");
                creditCard = (CreditCard) creditCardDao.get(id);
                logger.info("creditCard get: " + creditCard);
                if (creditCard.getCreditCardStatus() == 2) messages = "Credit card is already locked";
                if (creditCard.getCreditCardStatus() == 1) {
                    creditCard.setCreditCardStatus(2);
                    logger.info("creditCard update: " + creditCard);
                    creditCardDao.saveOrUpdate(creditCard);
                    messages = "Credit card is blocked";
                }

            } catch (Exception e1) {
                logger.error("Error1");
            }
        } else messages = "you card with this id does not belong";

        return messages;
    }

    @Override
    public String blockCard(int idCard) {

        try {
            if (existCard(idCard) >= 0) {
                creditCard = (CreditCard) creditCardDao.get(idCard);
                logger.info("get creditCard : " + creditCard.getCreditCardStatus() + "  " + creditCard);
                if (creditCard.getCreditCardStatus() == 1) {
                    logger.info("set status");
                    creditCard.setCreditCardStatus(2);
                    creditCardDao.saveOrUpdate(creditCard);
                    logger.info("blok creditCard : " + creditCard);
                    messages = "succefull";
                } else messages = "Credit card is already locked";
            } else messages = "the credit card does not exist";
        } catch (Exception e) {
            logger.error("Error1" + creditCard);
        }

        return messages;
    }

    @Override
    public String unlockCard(int idCard) {
        try {
            if (existCard(idCard) >= 0) {
                creditCard = (CreditCard) creditCardDao.get(idCard);
                if (creditCard.getCreditCardStatus() == 2) {
                    creditCard.setCreditCardStatus(1);
                    creditCardDao.saveOrUpdate(creditCard);
                    messages = "succefull";
                } else messages = "Credit card is already unlocked";
            } else messages = "the credit card does not exist";
        } catch (Exception e) {
            logger.error("Error1");
        }

        return messages;
    }

    @Override
    public int existCard(int id) {

        try {
            List<CreditCard> creditCards = creditCardDao.getAll();
            ArrayList<Integer> idCardList = new ArrayList<>();
            for (CreditCard creditCard : creditCards) {
                idCardList.add(creditCard.getId());
            }
            result = idCardList.indexOf(id);
        } catch (Exception ignored) {
            logger.error("Error1");
        }
        return result;
    }

    @Override
    public String payOrderUser(User user, int idSender, int sum) {
        if (verifyCardByUser(user, idSender) >= 0) {
            try {
                CreditCard creditCardSender = (CreditCard) creditCardDao.get(idSender);
                Account account = (Account) accountDao.get(creditCardSender.getAccount().getId());
                if (creditCardSender.getCreditCardStatus() == 1) {
                    if (account.getBalance() >= sum) {
                        account.setBalance(account.getBalance() - sum);
                        accountDao.saveOrUpdate(account);
                        messages = "successful";
                        paymentService.createPayment(idSender, sum, user.getId());
                    } else messages = "insufficient funds";
                }
                if (creditCardSender.getCreditCardStatus() == 2) messages = "Credit card is blocked";
            } catch (Exception e) {
                logger.error("Error1");
            }
        } else messages = "you card with this id does not belong";
        return messages;
    }

    @Override
    public String transferMoneyUser(User user, int idSender, int idRecipient, int sum) {
        if (verifyCardByUser(user, idSender) >= 0) {
            if (existCard(idRecipient) >= 0) {
                try {
                    CreditCard creditCardSender = (CreditCard) creditCardDao.get(idSender);
                    Account accountSender = (Account) accountDao.get(creditCardSender.getAccount().getId());
                    CreditCard creditCardRecipient = (CreditCard) creditCardDao.get(idRecipient);
                    Account accountRecipient = (Account) accountDao.get(creditCardRecipient.getAccount().getId());
                    if (creditCardSender.getCreditCardStatus() == 1) {
                        if (creditCardRecipient.getCreditCardStatus() == 1) {
                            if (accountSender.getBalance() >= sum) {
                                accountSender.setBalance(accountSender.getBalance() - sum);
                                accountRecipient.setBalance(accountRecipient.getBalance() + sum);
                                accountDao.saveOrUpdate(accountRecipient);
                                accountDao.saveOrUpdate(accountSender);
                                messages = "successful";
                                transferService.createTransfer(idSender, idRecipient, sum, user.getId());
                            } else messages = "insufficient funds";
                        }
                        if (creditCardRecipient.getCreditCardStatus() == 2)
                            messages = "Recipient's credit card is blocked";
                    }
                    if (creditCardSender.getCreditCardStatus() == 2) messages = "Sender's credit card is blocked";
                } catch (Exception e) {
                    logger.error("Error1");
                }
            } else messages = "the sender's credit card does not exist";
        } else messages = "you card with this id does not belong";
        return messages;
    }

    @Override
    public int verifyCardByUser(User user, int id) {

        try {
            Account account = (Account) accountDao.getByIdUser(user.getId());
            List<CreditCard> creditCards = creditCardDao.getAllByIdAccount(account.getId());
            ArrayList<Integer> idCardList = new ArrayList<>();
            for (CreditCard creditCard : creditCards) {
                idCardList.add(creditCard.getId());
            }
            result = idCardList.indexOf(id);

        } catch (Exception ignored) {
            logger.error("Error1");
        }
        return result;
    }

    @Override
    public String createCreditCard(int idAccount, int idUser) {

        try {
            if (accountService.checkPermission(idUser, idAccount)) {
                Account account = (Account) accountDao.get(idAccount);
                List creditCards = creditCardDao.getAllByIdAccount(account.getId());
                if (creditCards.size() <= 19) {
                    CreditCard creditCard = new CreditCard(1, account, 1, new Date());
                    creditCardDao.saveOrUpdate(creditCard);
                    messages = "Credit card created";
                } else messages = "you have 19 card";
            } else messages = "error";
        } catch (Exception e) {
            messages = "Credit card hasn't created";
        }
        return messages;
    }

    public List getCreditCardsLimit(int startNumber, int countFields) {
        return (List<CreditCard>) creditCardDao.getAllLimit(startNumber, countFields);
    }

    @Override
    public List getCreditCardsLimitByUser(int idUser) {
        return (List<CreditCard>) creditCardDao.getAllLimitByUser(idUser);
    }

    public int getCountCreditCards() {
        return creditCardDao.getCount();
    }

    @Override
    public int getCountCreditCardsByUser(int id) {
        return creditCardDao.getCountByUser(id);
    }


    public List getAllcreditCardsStatus() {
        return creditCardStatusDao.getAll();
    }
}
