package dao;

import beans.Game;
import beans.Player;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class MySqlDaoFactory implements DaoFactory {
    private String DRIVER;
    private String DB;
    private String USER;
    private String PASSWORD;
    private static final Logger log = Logger.getLogger(MySqlDaoFactory.class);
    private Map<Class, DaoCreator> daos;

    public MySqlDaoFactory() throws IOException {
        loadDBproperties();
        DriverRegistration();
        loadDaos();
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            log.info("Create connection with DB");
            connection = DriverManager.getConnection(DB, USER, PASSWORD);
            log.info("Success connection");
        } catch (SQLException e) {
            log.error("Registration done");
        }
        return connection;
    }

    @Override
    public DaoGenerick getDao(Connection connection, Class daoClass) throws DaoException {
        DaoCreator daoCreator = daos.get(daoClass);
        if (daoCreator == null) {
            throw new DaoException("Dao for class " + daoClass + "not found");
        }
        return daoCreator.create(connection);
    }


    private void loadDBproperties() throws IOException {
        Properties properties = new Properties();
        log.info("Getting db properties from file");
        properties.load(DaoGenerick.class.getResourceAsStream("/db.properties"));
        DRIVER = properties.getProperty("DRIVER");
        DB = properties.getProperty("DB");
        USER = properties.getProperty("USER");
        PASSWORD = properties.getProperty("PASSWORD");
        log.info("Loading db properties done");
    }

    private void DriverRegistration() {
        try {
            log.info("Starting JDBC driver registration");
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("Problem with driver registration ");
            log.error(e.getStackTrace());
        }
    }

    private void loadDaos() {
        daos = new HashMap<>();
        daos.put(Player.class, new DaoCreator() {
            @Override
            public DaoGenerick create(Connection connection) {
                return new PlayerDao(connection);
            }
        });

        daos.put(Game.class, new DaoCreator() {
            @Override
            public DaoGenerick create(Connection connection) {
                return new GameDao(connection);
            }
        });
    }
}
