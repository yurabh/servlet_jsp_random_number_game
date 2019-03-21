package filters;

import beans.Game;
import beans.Player;
import dao.DaoException;
import dao.DaoFactory;
import dao.DaoGenerick;
import dao.MySqlDaoFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeDataPlayerToDb implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();
        Game game = (Game) httpSession.getAttribute("game1");
        try {
            changePlayerDb(game);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    private void changePlayerDb(Game game) throws DaoException {
        try {
            DaoFactory factory = new MySqlDaoFactory();
            DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Player.class);
            Player player = new Player(game.getPlayer());
            player = (Player) daoGenerick.read(player.getUserName());
            if (game.getWin() != 0) {
                player.setBalance(player.getBalance() + game.getWin());
                player.setLastBet(game.getRate());
            } else {
                player.setBalance(player.getBalance() - game.getRate());
                player.setLastBet(game.getRate());
            }
            daoGenerick.update(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}
