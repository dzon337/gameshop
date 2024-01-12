package com.example.backend.controller;

import java.util.*;

import com.example.backend.service.GameService;
import com.example.backend.service.DatabaseFiller;
import com.example.backend.report.BestSellerGameReport;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MainController {

    @Autowired
    private DatabaseFiller dbFiller;

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public String homepage() {
        return "fillDatabase";
    }

    @GetMapping("/bestseller")
    public ResponseEntity<?> bestSellerReport() {
        try {
            final List<BestSellerGameReport> bestsellers = this.gameService.getBestsellerReport();
            return ResponseEntity.ok(bestsellers);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/bestsellerReport")
    public String redirectToBestseller() {
        return "bestsellerReport";
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