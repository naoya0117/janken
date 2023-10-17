package oit.is.z1992.kaizi.janken.controller;

import oit.is.z1992.kaizi.janken.model.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.ui.ModelMap;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {
    @Autowired
    private Entry entry;
    @Autowired
    private UserMapper usermapper;
    @Autowired
    private MatchMapper matchmapper;

    @GetMapping("/janken")
    public String janken(Principal prin, ModelMap model) {
        String username = prin.getName();
        this.entry.addUser(username);
        ArrayList<User> users = usermapper.selectAllUsers();
        ArrayList<Match> matches = matchmapper.selectAllMatches();
        model.addAttribute("users", users);
        model.addAttribute("matches", matches);

        return "janken.html";
    }

    @GetMapping("/match")
    public String match(@RequestParam String id , ModelMap model ) {
        User opp = usermapper.selectById(id);
        model.addAttribute("opponent", opp);
        return "match.html";
    }
    @GetMapping("/fight")
    public String game(@RequestParam String id, @RequestParam String hand , ModelMap model, Principal prin) {
        Match match = new Match();

        Janken janken = new Janken(hand);

        match.setUser1(usermapper.selectByName(prin.getName()).getId());
        match.setUser2(Integer.parseInt(id));
        match.setUser1Hand(janken.getPlayer());
        match.setUser2Hand(janken.getCpu());
        matchmapper.insertMatch(match);

        model.addAttribute("opponent", usermapper.selectById(id));
        model.addAttribute("player", janken.getPlayer());
        model.addAttribute("Cpu", janken.getCpu());
        model.addAttribute("result", janken.getResult());

        return "match.html";
    }
}