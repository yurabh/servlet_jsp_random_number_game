package dao;

import exception.DaoException;

import java.sql.Connection;

public interface DaoFactory {
    Connection getConnection();

    DaoGeneric getDao(Connection connection, Class daoClass) throws DaoException;
}
