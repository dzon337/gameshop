package com.example.backend.report;

import lombok.*;

import java.util.Set;
import java.util.HashSet;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BestSellerGameReport {

    private String gameName;
    private Float gamePrice;
    @Builder.Default
    private Set<String> genreNames = new HashSet<>();
    private BigDecimal soldCopies;
    private Double totalProfit;

}