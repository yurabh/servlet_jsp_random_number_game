package servlets;

import beans.Game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = {"/game-servlet"})
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        Game game = (Game) httpSession.getAttribute("game");
        String answerResult = enterNumber(game, req);

        if (answerResult.equals("LESS NUMBER")) {
            httpSession.setAttribute("answer", answerResult);
            httpSession.setAttribute("rate", 0);
            httpSession.setAttribute("game", game);
            resp.sendRedirect("/game.jsp");
        } else if (answerResult.equals("BIGGER NUMBER")) {
            httpSession.setAttribute("answer", answerResult);
            httpSession.setAttribute("rate", 0);
            httpSession.setAttribute("game", game);
            resp.sendRedirect("/game.jsp");
        } else if (answerResult.equals("GUESS NUMBER")) {
            httpSession.setAttribute("answer", answerResult);
            httpSession.setAttribute("rate", game.getRate() * 2);
            httpSession.setAttribute("game", game);
            resp.sendRedirect("/game-over.jsp");
        } else if (answerResult.equals("GAME OVER")) {
            httpSession.setAttribute("answer", answerResult);
            httpSession.setAttribute("rate", 0);
            httpSession.setAttribute("game", game);
            resp.sendRedirect("/game-over.jsp");
        }
    }

    private String enterNumber(Game game, HttpServletRequest req) {
        Integer number = Integer.valueOf(req.getParameter("number"));
        return game.checkAnswer(number);
    }
}
