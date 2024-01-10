package com.example.front_end.views;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameCatalogView {
    @GetMapping("/home")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @GetMapping("/checkout")
    public String checkout (Model model) {
        return "checkout";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "profile";
    }

}