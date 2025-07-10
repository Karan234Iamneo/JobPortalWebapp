package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register/jobseeker")
    public User registerJobSeeker(@RequestBody JobSeekerRegisterRequest request) {
        return service.registerJobSeeker(request);
    }

    @PostMapping("/register/recruiter")
    public User registerRecruiter(@RequestBody CompanyRegisterRequest request) {
        return service.registerCompany(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return service.login(request);
    }
}
