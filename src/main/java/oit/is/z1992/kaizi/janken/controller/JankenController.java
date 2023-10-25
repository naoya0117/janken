package oit.is.z1992.kaizi.janken.controller;

import oit.is.z1992.kaizi.janken.model.*;
import oit.is.z1992.kaizi.janken.service.AsyncKekka;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class JankenController {
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private MatchMapper matchmapper;
    @Autowired
    private MatchInfoMapper matchinfomapper;
    @Autowired
    private AsyncKekka asynckekka;

    @GetMapping("/janken")
    public String janken(ModelMap model) {
        ArrayList<User> users = usermapper.selectAllUsers();
        ArrayList<Match> matches = matchmapper.selectAllMatches();
        ArrayList<MatchInfo> activeMatches = matchinfomapper.selectActiveMatches();
        ArrayList<JankenResult> results  = new ArrayList<>();

        for (Match match : matches) {
            JankenResult result = new JankenResult(match);
            results.add(result);
        }
        model.addAttribute("users", users);
        model.addAttribute("history", results);

        model.addAttribute("activematches", activeMatches);

        return "janken.html";
    }

    @GetMapping("/match")
    public String match(@RequestParam String id , ModelMap model ) {
        User opp = usermapper.selectById(id);
        model.addAttribute("opponent", opp);
        return "match.html";
    }


    @GetMapping("/wait")
    public String wait(@RequestParam String id, @RequestParam String hand, ModelMap model, Principal prin) {
        String user1name = prin.getName();
        int user1 = usermapper.selectByName(user1name).getId();
        int user2 = Integer.parseInt(id);
        MatchInfo game = matchinfomapper.selectActiveMatchInfoByUser(user1);

        if(game == null) {
            MatchInfo matchinfo = new MatchInfo();
            matchinfo.setUser1(user1);
            matchinfo.setUser2(user2);
            matchinfo.setUser1Hand(hand);
            matchinfo.setActive(true);
            matchinfomapper.insertMatchInfo(matchinfo);
        } else {
            Match match = new Match();
            match.setUser1(user1);
            match.setUser2(user2);
            match.setUser1Hand(hand);
            match.setUser2Hand(game.getUser1Hand());
            match.setActive(true);
            this.asynckekka.syncInsertMatchTable(match);
        }

        return "wait.html";
    }

    @GetMapping("/fight")
    public String game(@RequestParam String id, @RequestParam String hand , ModelMap model, Principal prin) {
        Match match = new Match();

        Janken janken = new Janken(hand);

        match.setUser1(usermapper.selectByName(prin.getName()).getId());
        match.setUser2(Integer.parseInt(id));
        match.setUser1Hand(janken.getPlayer1());
        match.setUser2Hand(janken.getPlayer2());
        match.setActive(false);
        matchmapper.insertMatch(match);

        model.addAttribute("opponent", usermapper.selectById(id));
        model.addAttribute("player1", janken.getPlayer1());
        model.addAttribute("player2", janken.getPlayer2());
        model.addAttribute("result", janken.getResult());

        return "match.html";
    }

    @GetMapping("/sse")
    public SseEmitter sse() throws IOException {
        final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        this.asynckekka.kekka(emitter);
        return emitter;
    }
}