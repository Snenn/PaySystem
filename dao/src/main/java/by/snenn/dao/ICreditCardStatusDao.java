package by.snenn.dao;

import java.util.List;


public interface ICreditCardStatusDao<TYPE> extends IDao<TYPE> {
    List getAll();
}
