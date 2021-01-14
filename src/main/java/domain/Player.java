package domain;

import java.util.Objects;

public class Player {

    private String id;
    private String userName;
    private int balance;
    private int lastBet;

    public Player() {
    }

    public Player(Player player) {
        this.id = player.id;
        this.userName = player.userName;
        this.balance = player.balance;
        this.lastBet = player.lastBet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getLastBet() {
        return lastBet;
    }

    public void setLastBet(int lastBet) {
        this.lastBet = lastBet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return balance == player.balance &&
                lastBet == player.lastBet &&
                Objects.equals(id, player.id) &&
                Objects.equals(userName, player.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, balance, lastBet);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", lastBet=" + lastBet +
                '}';
    }
}
