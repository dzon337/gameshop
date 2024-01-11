package com.example.backend.model.request;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {

    private String fromUsername;
    private String toUsername;

}