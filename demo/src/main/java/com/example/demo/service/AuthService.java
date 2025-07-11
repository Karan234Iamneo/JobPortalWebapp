package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtUtil;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepo;
    private final JobSeekerRepository jobSeekerRepo;
    private final CompanyRepository companyRepo;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepo, JobSeekerRepository jobSeekerRepo, CompanyRepository companyRepo, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.jobSeekerRepo = jobSeekerRepo;
        this.companyRepo = companyRepo;
        this.jwtUtil = jwtUtil;
    }

    public User registerJobSeeker(JobSeekerRegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        User user = new User(null, request.getName(), request.getEmail(), request.getPhone(),
                request.getAddress(), User.Role.jobseeker, request.getPassword());
        user = userRepo.save(user);

        JobSeeker js = new JobSeeker();
        js.setUser(user);
        js.setResumeUrl(request.getResumeUrl());
        js.setSkills(request.getSkills());
        js.setJobRoleInterests(request.getJobRoleInterests());

        jobSeekerRepo.save(js);

        return user;
    }

    public User registerCompany(CompanyRegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered.");
        }

        User user = new User(null, request.getName(), request.getEmail(), request.getPhone(),
                request.getAddress(), User.Role.recruiter, request.getPassword());
        user = userRepo.save(user);

        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setCompanyLocation(request.getCompanyLocation());
        company.setAbout(request.getAbout());
        company.setRecruiter(user);
        company.setStatus(Company.Status.in_review);

        companyRepo.save(company);

        return user;
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
    
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    
        return new AuthResponse(user.getUserId(), user.getRole(), "Login successful", token);
    }
}
