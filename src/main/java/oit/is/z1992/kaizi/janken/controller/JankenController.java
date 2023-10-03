package oit.is.z1992.kaizi.janken.controller;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {
    @PostMapping("/janken")
    public String janken(ModelMap model, @RequestParam String username) {
        model.addAttribute("username", username);
        return "janken.html";
    }
}