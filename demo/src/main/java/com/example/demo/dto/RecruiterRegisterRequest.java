package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterRegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;

    private String companyName;
    private String companyLocation;
    private String about;
}
