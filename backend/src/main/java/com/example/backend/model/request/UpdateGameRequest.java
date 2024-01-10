package com.example.backend.model.request;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGameRequest {

    private String newName;
    private Float newPrice;

}