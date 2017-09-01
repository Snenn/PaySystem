package by.snenn.services;


import java.util.List;

public interface ITransferService {

    List getAllTransfers();

    String createTransfer(int idCardSender, int idCardRecepient, int sum, int idUser );

    List getTransfersLimit(int startNumber, int countFields);

    List getTransfersLimitByUser(int idUser);

    int getCountTransfers();
}
