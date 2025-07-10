package com.example.demo.dto;

import com.example.demo.entity.User.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private Role role;
    private String password;
}
