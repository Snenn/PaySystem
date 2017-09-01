package by.snenn.services;

import java.util.List;

public interface IPaymentService {

    List getAllPayment ();

    String createPayment (int idCard, int sum, int idUser);

    List getPaymentsLimit(int startNumber, int countFields);

    List getPaymentsLimitByUser(int idUser);

    int getCountPayments();

}
