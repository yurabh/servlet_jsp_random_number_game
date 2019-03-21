import beans.Player;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoGenerick;
import dao.MySqlDaoFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class TestPlayerDao {
    @Test
    public void cretePlayer() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        Player playerOne = new Player();
        Integer randomNumber = new Random().nextInt(300);
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Player.class);
        playerOne.setId(String.valueOf(String.valueOf(randomNumber)));
        playerOne.setUserName("yuar");
        playerOne.setBalance(0);
        playerOne.setLastBet(0);
        playerOne = (Player) daoGenerick.create(playerOne);
    }

    @Test
    public void readByKey() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Player.class);
        Player player = new Player();
        player = (Player) daoGenerick.read("yuar");
        System.out.println(player.toString());
    }

    @Test
    public void updatePlayer() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Player.class);
        Player player = new Player();
        player = (Player) daoGenerick.read("yuar");
        player.setId("279");
        player.setUserName("yura");
        player.setBalance(12);
        player.setLastBet(1200);
        daoGenerick.update(player);
    }

    @Test
    public void deletePlayer() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Player.class);
        Player player = new Player();
        player = (Player) daoGenerick.read("yuar");
        daoGenerick.delete(player);
    }
}
