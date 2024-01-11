package com.example.backend.controller;

import java.util.*;
import java.math.BigDecimal;

import com.example.backend.service.DatabaseFiller;
import com.example.backend.repository.IGameRepository;
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
    private IGameRepository gameRepo;

    @GetMapping("/")
    public String homepage() {
        return "fillDatabase";
    }

    @GetMapping("/bestSellerReport")
    public ResponseEntity<?> bestSellerReport() {
        try {
            final List<Object[]> dbResponse = this.gameRepo.bestSellerGames();

            final Map<String, BestSellerGameReport> gameReportsMap = new HashMap<>();

            for (Object[] objectArray : dbResponse) {
                String gameName = (String) objectArray[0];

                final BestSellerGameReport gameReport = gameReportsMap.computeIfAbsent(gameName, key ->
                        BestSellerGameReport.builder()
                                .gameName(gameName)
                                .gamePrice((Float) objectArray[1])
                                .soldCopies(new BigDecimal(((Number) objectArray[3]).doubleValue()))
                                .totalProfit((Double) objectArray[4])
                                .build()
                );

                gameReport.getGenreNames().add((String) objectArray[2]);
            }

            List<BestSellerGameReport> sortedGames = new ArrayList<>(gameReportsMap.values());

            return ResponseEntity.ok(sortedGames);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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