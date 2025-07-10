package com.example.demo.controller;

import com.example.demo.entity.JobSeeker;
import com.example.demo.service.JobSeekerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    private final JobSeekerService service;

    public JobSeekerController(JobSeekerService service) {
        this.service = service;
    }

    @PostMapping
    public JobSeeker create(@RequestBody JobSeeker jobSeeker) {
        return service.createJobSeeker(jobSeeker);
    }

    @GetMapping("/{id}")
    public Optional<JobSeeker> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping
    public List<JobSeeker> getAll() {
        return service.getAll();
    }
}
