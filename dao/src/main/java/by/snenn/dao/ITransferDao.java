package by.snenn.dao;

import by.snenn.pojos.Transfer;

import java.util.List;

public interface ITransferDao extends IDao {
    List<Transfer> getAll();

    List<Transfer> getAllLimit(int startNumber, int countFields);

    List<Transfer> getAllLimitByUser(int idUser);

    int getCount();


}
