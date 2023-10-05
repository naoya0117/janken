package oit.is.z1992.kaizi.janken.controller;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z1992.kaizi.janken.model.Janken;

@Controller
public class JankenController {
    @PostMapping("/janken")
    public String janken(ModelMap model, @RequestParam String username) {
        model.addAttribute("username", username);
        return "janken.html";
    }
    @GetMapping("/jankengame")
    public String game(@RequestParam String hand , ModelMap model) {
        Janken janken = new Janken(hand);

        model.addAttribute("player_hand", janken.getPlayer());
        model.addAttribute("cpu_hand", janken.getCpu());
        model.addAttribute("result",janken.getResult());

        return "janken.html";
    }
}