package beans;

import java.util.Objects;
import java.util.Random;

public class Game {
    private Integer id;
    private Player player;
    private Integer rate;
    private Integer randomNumber;
    private int counter;
    private int win;
    private int lose;

    public Game(Player player, Integer rate) {
        this.player = player;
        this.rate = rate;
        initGame();
    }

    public Game() {
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getWin() {
        return win;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getRate() {
        return rate;
    }

    public int getCounter() {
        return counter;
    }

    private void initGame() {
        player.setBalance(player.getBalance() + rate);
        randomNumber = new Random().nextInt(10);
        counter = 0;
    }

    public String checkAnswer(Integer number) {
        if ((counter++) <= 5) {
            if (number.equals(randomNumber)) {
                return "GUESS NUMBER";
            } else if (number.compareTo(randomNumber) < 0) {
                return "LESS NUMBER";
            } else {
                return "BIGGER NUMBER";
            }
        } else {
            return "GAME OVER";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return counter == game.counter &&
                win == game.win &&
                lose == game.lose &&
                Objects.equals(id, game.id) &&
                Objects.equals(player, game.player) &&
                Objects.equals(rate, game.rate) &&
                Objects.equals(randomNumber, game.randomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, player, rate, randomNumber, counter, win, lose);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", player=" + player +
                ", rate=" + rate +
                ", randomNumber=" + randomNumber +
                ", counter=" + counter +
                ", win=" + win +
                ", lose=" + lose +
                '}';
    }
}
