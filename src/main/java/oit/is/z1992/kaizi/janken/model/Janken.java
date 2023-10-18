package oit.is.z1992.kaizi.janken.model;

import org.apache.ibatis.jdbc.Null;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Janken {
    private final String player1;
    private final String player2;

    public Janken(String player) {
        this.player1 = player;

        Map<Integer, String> handMap =  new HashMap<>();
        handMap.put(1, "Gu");
        handMap.put(2, "Choki");
        handMap.put(3, "Pa");

        int key = new Random().nextInt(3) + 1;
        this.player2 = handMap.get(key);
    }

    public Janken(String player, String cpu) {
        this.player1 = player;
        this.player2 = cpu;
    }

    public String getWinner() {
        if (this.player1.equals(this.player2)) {
            return null;
        }
        if (this.player1.equals("Gu") && this.player2.equals("Choki") ||
                this.player1.equals("Choki") && this.player2.equals("Pa") ||
                this.player1.equals("Pa") && this.player2.equals("Gu")) {
            return "user1";
        }
        if (this.player2.equals("Gu") && this.player1.equals("Choki") ||
                this.player2.equals("Choki") && this.player1.equals("Pa") ||
                this.player2.equals("Pa") && this.player1.equals("Gu")) {
            return "user2";
        }

        return "Error";
    }

    public String getResult () {
        String winner = this.getWinner();
        if (winner == null) {
            return "Draw";
        }
        if (winner.equals("user1")) {
            return "You Win!";
        }
        if (winner.equals("user2")) {
            return "You Lose";
        }

        return "Error";
    }

    public String getPlayer2() {
        return this.player2;
    }
    public String getPlayer1() {
        return this.player1;
    }
}