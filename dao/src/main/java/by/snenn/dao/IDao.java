package by.snenn.dao;


import java.io.Serializable;

public interface IDao<TYPE> {

    void saveOrUpdate(TYPE type) throws Exception;

    void delete(TYPE type) throws Exception;

    TYPE get(Serializable id) throws Exception;


}
