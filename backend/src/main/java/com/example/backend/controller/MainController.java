package com.example.backend.controller;

import com.example.backend.service.DatabaseFiller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MainController {
    @Autowired
    private DatabaseFiller dbFiller;

    @GetMapping("/")
    public String homepage() {
        return "fillDatabase";
    }

    @PostMapping("/fillDatabase")
    public void fillDatabase() {
        try {
            this.dbFiller.insertDummyData();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

}