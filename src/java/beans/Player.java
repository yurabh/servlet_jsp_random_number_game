package beans;

import java.util.Objects;

public class Player {
    private String id;
    private String userName;
    private Integer balance;
    private Integer lastBet;

    public Player() {
    }

    public Player(Player player) {
        this.id = player.id;
        this.userName = player.userName;
        this.balance = player.balance;
        this.lastBet = player.lastBet;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLastBet() {
        return lastBet;
    }

    public void setLastBet(Integer lastBet) {
        this.lastBet = lastBet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(userName, player.userName) &&
                Objects.equals(balance, player.balance) &&
                Objects.equals(lastBet, player.lastBet);
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
