package filter;

import dao.DaoFactory;
import dao.DaoGeneric;
import dao.dao_factory.MySqlDaoFactory;
import domain.Game;
import exception.DaoException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

public class CreateDbGame implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();
        Game game = (Game) httpSession.getAttribute("game");
        try {
            saveGameSessionToDb(game, request);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

    private void saveGameSessionToDb(Game game, HttpServletRequest req)
            throws DaoException {
        try {
            DaoFactory factory = new MySqlDaoFactory();
            DaoGeneric daoGeneric = factory.getDao(factory.getConnection(), Game.class);
            int random = new Random().nextInt(300);
            game.setId(random);
            game.setPlayer(game.getPlayer());
            HttpSession httpSession = req.getSession();
            if (httpSession.getAttribute("rate").equals(0)) {
                game.setWin(0);
                game.setLose(game.getRate());
            } else {
                game.setWin(game.getRate() * 2);
                game.setLose(0);
            }
            httpSession.setAttribute("game1", game);
            daoGeneric.create(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}
