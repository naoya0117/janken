package oit.is.z1992.kaizi.janken.model;

import oit.is.z1992.kaizi.janken.model.Janken;
import oit.is.z1992.kaizi.janken.model.Match;

/**
 * じゃんけんの結果を格納するクラス
 */
public class JankenResult extends Match {
    private String result;

    public JankenResult(Match match) {

        super.setId(match.getId());
        super.setUser1(match.getUser1());
        super.setUser2(match.getUser2());
        super.setUser1Hand(match.getUser1Hand());
        super.setUser2Hand(match.getUser2Hand());

        Janken game = new Janken(match.getUser1Hand(), match.getUser2Hand());
        if (game.getWinner() == null) {
            this.setResult("引き分け");
        } else {
            this.setResult(game.getWinner() + "の勝利");
        }
    }

    public void setResult(String result) { this.result = result; }

    public String getResult() { return this.result; }
}
