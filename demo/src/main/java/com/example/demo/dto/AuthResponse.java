package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private Integer userId;
    private User.Role role;
    private String message;
    private String token;
}
