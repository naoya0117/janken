package oit.is.z1992.kaizi.janken.model;

import java.util.Random;

public class Janken {
    private String cpu;
    private final String player;

    public Janken(String player) {
        this.player = player;
        int rand = new Random().nextInt(3);

        switch (rand) {
            case 0:
                this.cpu = "Gu";
                break;
            case 1:
                this.cpu = "Choki";
                break;
            case 2:
                this.cpu = "Pa";
                break;
        }
    }

    public String getResult () {
        if (this.cpu.equals(this.player)) {
            return "Draw";
        }
        if (this.cpu.equals("Gu") && this.player.equals("Choki") || 
            this.cpu.equals("Choki") && this.player.equals("Pa") ||
            this.cpu.equals("Pa") && this.player.equals("Gu")) {

            return "You Lose";
        } 
        if (this.cpu.equals("Gu") && this.player.equals("Pa") || 
            this.cpu.equals("Choki") && this.player.equals("Gu") ||
            this.cpu.equals("Pa") && this.player.equals("Choki")) {

            return "You Win!";
        }
        return "Error";
    }

    public String getCpu() {
        return this.cpu;
    }
    public String getPlayer() {
        return this.player;
    }
}