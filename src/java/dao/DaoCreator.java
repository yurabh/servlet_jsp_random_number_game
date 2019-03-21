package dao;

import java.sql.Connection;

public interface DaoCreator {
    DaoGenerick create(Connection connection);
}
