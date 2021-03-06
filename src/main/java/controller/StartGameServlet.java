package controller;

import domain.Game;
import domain.Player;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StartGame", urlPatterns = {"/start-game"})
public class StartGameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession httpSession = req.getSession();
        Player player = (Player) httpSession.getAttribute("player");
        String rate = (req.getParameter("user-rate"));
        Game game = new Game(player, Integer.valueOf(rate));
        httpSession.setAttribute("game", game);
        resp.sendRedirect("/game.jsp");
    }
}
