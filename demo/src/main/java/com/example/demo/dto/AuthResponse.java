package com.example.demo.dto;

import com.example.demo.entity.User.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Integer userId;
    private Role role;
    private String message;
}
