package dao;

import beans.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao extends AbstractDao<Player, String> {

    public PlayerDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO `player`(id,userName,balance,lastBet) VALUES" + "(?,?,?,?)";
    }

    @Override
    protected String getSelectQuery() {
        return "Select * from `player` WHERE `userName`= ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM `player` WHERE `id`=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE `player` SET userName=?, balance=?,lastBet=? WHERE `id`=?";
    }

    @Override
    protected String getSelectAll() {
        return "SELECT * FROM `player`";
    }

    @Override
    protected void setInsertStatement(PreparedStatement preparedStatement, Player object) throws DaoException {
        try {
            preparedStatement.setString(1, String.valueOf(object.getId()));
            preparedStatement.setString(2, object.getUserName());
            preparedStatement.setString(3, String.valueOf(object.getBalance()));
            preparedStatement.setString(4, String.valueOf(object.getLastBet()));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setStatementKey(PreparedStatement preparedStatement, String key) throws DaoException {
        try {
            preparedStatement.setString(1, key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setSelectStatement(PreparedStatement preparedStatement, Player object) throws DaoException {
        try {
            preparedStatement.setString(1, String.valueOf(object.getId()));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected void setDeleteStatement(PreparedStatement preparedStatement, Player object) throws DaoException {
        try {
            preparedStatement.setString(1, String.valueOf(object.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUpdateStatement(PreparedStatement preparedStatement, Player object) throws DaoException {
        try {
            preparedStatement.setString(1, object.getUserName());
            preparedStatement.setString(2, String.valueOf(object.getBalance()));
            preparedStatement.setString(3, String.valueOf(object.getLastBet()));
            preparedStatement.setString(4, String.valueOf(object.getId()));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    protected List<Player> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Player> players = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Player player = new Player();
                player.setId(resultSet.getString(1));
                player.setUserName(resultSet.getString(2));
                player.setBalance(Integer.valueOf(resultSet.getString(3)));
                player.setLastBet(Integer.valueOf(resultSet.getString(4)));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
}
