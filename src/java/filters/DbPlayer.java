package filters;

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
import java.util.Random;

public class DbPlayer implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Player player = null;
        try {
            player = saveDatePlayerToDb(request);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("player", player);
        filterChain.doFilter(request, response);
    }

    private Player saveDatePlayerToDb(HttpServletRequest req) throws DaoException {
        String userName = req.getParameter("user-name");
        Player player = new Player();
        Player playerOne = new Player();
        try {
            DaoFactory factory = new MySqlDaoFactory();
            Integer randomNumber = new Random().nextInt(300);
            DaoGenerick daoGenerick = factory.getDao(factory.getConnection(), Player.class);
            player = (Player) daoGenerick.read(userName);
            if (player == null) {
                playerOne.setId(String.valueOf(String.valueOf(randomNumber)));
                playerOne.setUserName(userName);
                playerOne.setBalance(0);
                playerOne.setLastBet(0);
                playerOne = (Player) daoGenerick.create(playerOne);
                return playerOne;
            }
            playerOne = (Player) daoGenerick.read(userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerOne;
    }

    @Override
    public void destroy() {
    }
}
