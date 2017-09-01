package by.snenn.services;


import by.snenn.pojos.User;

import java.util.List;

public interface ICreditCardService {

    /*blocks the card after checking the user belongs*/
    String blockCardUser(User user, int id);

    String blockCard(int idCard);

    String unlockCard(int idCard);

    /*check the existence of the card*/
    int existCard(int id);

    String payOrderUser(User user, int idSender, int sum);

    String transferMoneyUser(User user, int idSender, int idRecipient, int sum);

    /*check accessories card user*/
    int verifyCardByUser(User user, int id);


    String createCreditCard(int idAccount, int idUser);

    List getCreditCardsLimit(int startNumber, int countFields);

    List getCreditCardsLimitByUser(int idUser);

    List getAllcreditCardsStatus();

    int getCountCreditCards();

    int getCountCreditCardsByUser(int id);


}
