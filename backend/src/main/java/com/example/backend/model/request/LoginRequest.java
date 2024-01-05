package com.example.backend.model.request;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    private String username;
    private String password;

}
