package domain;

import java.util.Objects;
import java.util.Random;

public class Game {
    private int id;
    private Player player = new Player();
    private int rate;
    private int randomNumber;
    private int counter;
    private int win;
    private int lose;

    public Game() {
    }

    public Game(Player player, int rate) {
        this.player = player;
        this.rate = rate;
        initGame();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id &&
                rate == game.rate &&
                randomNumber == game.randomNumber &&
                counter == game.counter &&
                win == game.win &&
                lose == game.lose &&
                Objects.equals(player, game.player);
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
}
