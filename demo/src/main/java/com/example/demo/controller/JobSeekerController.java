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

    @GetMapping("/{id}")
    public Optional<JobSeeker> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping
    public List<JobSeeker> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public Optional<JobSeeker> getByUserId(@PathVariable Integer userId) {
        return service.getByUserId(userId);
    }

    @PutMapping("/{id}")
    public JobSeeker updateJobSeeker(@PathVariable Integer id, @RequestBody JobSeeker updatedJobSeeker) {
        return service.updateJobSeeker(id, updatedJobSeeker);
    }
}
