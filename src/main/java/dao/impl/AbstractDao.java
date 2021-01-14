package dao.impl;

import exception.DaoException;
import dao.DaoGeneric;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDao<T, V> implements DaoGeneric<T, V> {

    private static final Logger log = Logger.getLogger(AbstractDao.class);

    private Connection connection = null;

    protected abstract String getInsertQuery();

    protected abstract String getSelectQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getSelectAll();

    protected abstract void setInsertStatement(PreparedStatement preparedStatement, T object) throws DaoException;

    protected abstract void setStatementKey(PreparedStatement preparedStatement, V key) throws DaoException;

    protected abstract void setSelectStatement(PreparedStatement preparedStatement, T object) throws DaoException;

    protected abstract void setDeleteStatement(PreparedStatement preparedStatement, T object) throws DaoException;

    protected abstract void setUpdateStatement(PreparedStatement preparedStatement, T object) throws DaoException;

    protected abstract List<T> parseResultSet(ResultSet resultSet) throws DaoException;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public AbstractDao() {
        super();
    }

    @Override
    public T create(T object) throws DaoException {
        String insertQuery = getInsertQuery();
        String selectLastRecord = getSelectQuery();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            log.trace("Insert into our DВ..");
            setInsertStatement(preparedStatement, object);
            int rows = preparedStatement.executeUpdate();
            log.trace("Finish inserting our query...");
            if (rows != 1) {
                throw new DaoException("Created more than one record: " + rows);
            }
        } catch (SQLException e) {
            log.error("problem with inserting our query into our Db");
            log.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectLastRecord)) {
            log.trace("Search query in our database");
            setSelectStatement(preparedStatement, object);
            ResultSet resultSet = preparedStatement.executeQuery();
            log.trace("We are putting our query into the database");
            List<T> objects = parseResultSet(resultSet);
            if (objects == null || objects.size() != 1) {
                return null;
            }
            log.trace("Our object is right we returned this object");
        } catch (SQLException e) {
            log.error("We couldn't put this query into a database");
            log.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return object;
    }

    @Override
    public T read(V key) throws DaoException {
        String readQuery = getSelectQuery();
        List<T> users = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(readQuery)) {
            log.trace("Search data in Db");
            setStatementKey(preparedStatement, key);
            log.trace("Read data from database");
            ResultSet resultSet = preparedStatement.executeQuery();
            users = parseResultSet(resultSet);
            log.trace("Found object for returning");
        } catch (SQLException e) {
            log.error("We couldn't find and read query in Db");
            log.error(e.getMessage());
        }

        if (users == null || users.size() == 0) {
            return null;
        }

        if (users.size() > 1) {
            throw new DaoException("Returned more than one record");
        }
        return users.iterator().next();
    }

    @Override
    public T update(T object) throws DaoException {
        String updateQuery = getUpdateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            log.trace("Update query in DataBase");
            setUpdateStatement(preparedStatement, object);
            preparedStatement.executeUpdate();
            log.trace("Query is found and updated");
        } catch (SQLException e) {
            log.trace("We couldn't update query in Db");
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(T object) throws DaoException {
        String deleteQuery = getDeleteQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            log.trace("We search query for deleting");
            setDeleteStatement(preparedStatement, object);
            preparedStatement.executeUpdate();
            log.trace("This query which we found deleted");
        } catch (SQLException e) {
            log.error("We couldn't delete query");
            log.error(e.getMessage());
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        String selectAll = getSelectAll();
        List<T> users = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
