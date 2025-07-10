package com.example.demo.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerRegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String resumeUrl;
    private List<String> skills;
    private List<String> jobRoleInterests;
}
