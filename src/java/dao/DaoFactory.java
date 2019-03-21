package dao;

import java.sql.Connection;

public interface DaoFactory {
    Connection getConnection();

    DaoGenerick getDao(Connection connection, Class daoClass) throws DaoException;
}
