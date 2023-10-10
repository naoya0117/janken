package oit.is.z1992.kaizi.janken.controller;

import org.springframework.ui.ModelMap;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1992.kaizi.janken.model.Janken;
import oit.is.z1992.kaizi.janken.model.Entry;

@Controller
public class JankenController {
    @Autowired
    private Entry entry;

    @GetMapping("/janken")
    public String janken(Principal prin,ModelMap model) {
        String username = prin.getName();
        this.entry.addUser(username);
        model.addAttribute("username", username);
        model.addAttribute("users", this.entry.getUsers());
        model.addAttribute("num_users", this.entry.getNumUsers());
        return "janken.html";
    }
    @GetMapping("/jankengame")
    public String game(@RequestParam String hand , ModelMap model) {
        Janken janken = new Janken(hand);

        model.addAttribute("users", this.entry.getUsers());
        model.addAttribute("num_users", this.entry.getNumUsers());
        model.addAttribute("player_hand", janken.getPlayer());
        model.addAttribute("cpu_hand", janken.getCpu());
        model.addAttribute("result",janken.getResult());

        return "janken.html";
    }
}