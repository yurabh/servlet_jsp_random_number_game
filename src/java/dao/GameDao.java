package dao;

import beans.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameDao extends AbstractDao<Game, String> {
    public GameDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO `game` (`time`,`played_id`,`win`,`lose`) VALUES " + "(?,?,?,?)";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM `game` WHERE `id`= ? ";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM `game` WHERE `id`= ?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE `game` SET userName=? ,player_id = ? , win=? , lose= ? WHERE `id` = ?";
    }

    @Override
    protected String getSelectAll() {
        return "SELECT * FROM `game`";
    }

    @Override
    protected void setInsertStatement(PreparedStatement preparedStatement, Game object) throws DaoException {
        try {
            // preparedStatement.setString(1, String.valueOf(object.getId()));
            preparedStatement.setString(1, String.valueOf(LocalDateTime.now()));
            preparedStatement.setString(2, String.valueOf(object.getPlayer().getId()));
            preparedStatement.setString(3, String.valueOf(object.getWin()));
            preparedStatement.setString(4, String.valueOf(object.getLose()));
        } catch (SQLException e) {
            e.printStackTrace();
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
    protected void setSelectStatement(PreparedStatement preparedStatement, Game object) throws DaoException {
        try {
            preparedStatement.setString(1, String.valueOf(object.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setDeleteStatement(PreparedStatement preparedStatement, Game object) throws DaoException {
        try {
            preparedStatement.setString(1, String.valueOf(object.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUpdateStatement(PreparedStatement preparedStatement, Game object) throws DaoException {
        try {
            preparedStatement.setString(1, String.valueOf(object.getPlayer()));
            preparedStatement.setString(2, String.valueOf(object.getWin()));
            preparedStatement.setString(3, String.valueOf(object.getLose()));
            preparedStatement.setString(4, String.valueOf(object.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Game> parseResultSet(ResultSet resultSet) throws DaoException {
        List<Game> games = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Game game = new Game();
                game.setId(Integer.valueOf(resultSet.getString(1)));
                //game.setPlayer(resultSet.getString(2));
                game.setRate(Integer.valueOf(resultSet.getString(3)));
                game.setWin(Integer.valueOf(resultSet.getString(4)));
                game.setLose(Integer.parseInt(resultSet.getString(5)));
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
}
