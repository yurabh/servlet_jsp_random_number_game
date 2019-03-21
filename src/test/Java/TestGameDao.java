import beans.Game;
import beans.Player;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoGenerick;
import dao.MySqlDaoFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;


public class TestGameDao {
    @Test
    public void testCreateGame() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Game.class);
        Game game = new Game();
        game.setId(2);
        Player player = new Player();
        Integer randomNumber = new Random().nextInt(300);
        player.setId(String.valueOf(String.valueOf(randomNumber)));
        player.setUserName("yuar");
        player.setBalance(0);
        player.setLastBet(0);
        game.setPlayer(player);
        game.setRate(0);
        game.setWin(0);
        game.setLose(0);
        daoGenerick.create(game);
    }

    @Test
    public void testReadByKey() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Game.class);
        Game game = new Game();
        game = (Game) daoGenerick.read("2");
        System.out.println(game.toString());
    }

    @Test
    public void testUpdateGame() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Game.class);
        Game game = new Game();
        game = (Game) daoGenerick.read("2");
        game.setLose(11111);
        game.setWin(11111);
        game.setRate(1222);
        game.setId(1);
        daoGenerick.update(game);
    }

    @Test
    public void testDeleteGame() throws IOException, DaoException {
        DaoFactory factory = new MySqlDaoFactory();
        DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Game.class);
        Game game = new Game();
        game = (Game) daoGenerick.read("2");
        daoGenerick.delete(game);
    }
}
