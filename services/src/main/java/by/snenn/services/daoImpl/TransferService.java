package by.snenn.services.daoImpl;


import by.snenn.dao.IAccountDao;
import by.snenn.dao.ICreditCardDao;
import by.snenn.dao.ITransferDao;
import by.snenn.pojos.Account;
import by.snenn.pojos.CreditCard;
import by.snenn.pojos.Transfer;
import by.snenn.services.IAccountService;
import by.snenn.services.ICreditCardService;
import by.snenn.services.ITransferService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class TransferService implements ITransferService {


    @Autowired
    private ITransferDao transferDao;
    @Autowired
    private ICreditCardDao creditCardDao;
    @Autowired
    private ICreditCardService creditCardService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountDao accountDao;

    private Logger logger = Logger.getLogger(CreditCardService.class.getName());
    private String messages = null;

    @Override
    public List getAllTransfers() {
        List<Transfer> transfers = transferDao.getAll();
        return transfers;
    }

    @Override
    public String createTransfer(int idCard, int sum, int idCardRecipient,  int idUser) {
        try {
            CreditCard card = (CreditCard) creditCardDao.get(idCard);
            if (creditCardService.existCard(idCardRecipient) >= 0) {
                CreditCard cardRecipient = (CreditCard) creditCardDao.get(idCardRecipient);
                if (card.getCreditCardStatus() == 1) {
                    if (accountService.checkPermission(idUser, card.getAccount().getId())) {
                        Account account = (Account) accountDao.get(card.getAccount().getId());
                        Account accountRecipient = (Account) accountDao.get(cardRecipient.getAccount().getId());
                        if (account.getBalance() >= sum) {
                            if (cardRecipient.getCreditCardStatus() == 1) {
                                Transfer transfer = new Transfer();
                                transfer.setSum(sum);
                                transfer.setCardSender(card.getId());
                                transfer.setCardRecipient(cardRecipient.getId());
                                transfer.setData(Calendar.getInstance().getTime());
                                account.setBalance(account.getBalance() - sum);
                                accountRecipient.setBalance(accountRecipient.getBalance() + sum);
                                accountDao.saveOrUpdate(account);
                                accountDao.saveOrUpdate(accountRecipient);
                                transferDao.saveOrUpdate(transfer);
                                messages = "transfer save";
                            } else messages = "card recipient is blocked";
                        } else messages = "no money";
                    } else messages = "error123";
                } else messages = "card is blocked";
            } else messages = "card recipient don't exist";
        } catch (Exception e) {
            messages = "transfer error, please try again";
        }
        return messages;
    }

    @Override
    public List getTransfersLimit(int startNumber, int countFields) {
        return (List<Transfer>) transferDao.getAllLimit(startNumber, countFields);
    }

    @Override
    public List getTransfersLimitByUser(int idUser) {
        return (List<Transfer>) transferDao.getAllLimitByUser(idUser);
    }

    @Override
    public int getCountTransfers() {
        return transferDao.getCount();
    }
}
