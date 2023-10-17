package oit.is.z1992.kaizi.janken.model;

public class Match {
    private int id;
    private String user1;
    private String user2;
    private String user1Hand;
    private String user2Hand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getUser1Hand() {
        return user1Hand;
    }

    public void setUser1Hand(String user1Hand) {
        this.user1Hand = user1Hand;
    }

    public String getUser2Hand() {
        return user2Hand;
    }

    public void setUser2Hand(String user2Hand) {
        this.user2Hand = user2Hand;
    }
}