package com.example.demo.controller;

import com.example.demo.entity.Jobs;
import com.example.demo.service.JobsService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

    private final JobsService service;

    public JobsController(JobsService service) {
        this.service = service;
    }

    @PostMapping
    public Jobs create(@RequestBody Jobs job) {
        return service.createJob(job);
    }

    @GetMapping("/{id}")
    public Optional<Jobs> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    // Publicly accessible - list all published jobs
    @GetMapping("/status/published")
    public List<Jobs> getPublishedJobs() {
        return service.getPublishedJobs();
    }

    // Publicly accessible - get single job only if published
    @GetMapping("/published/{id}")
    public ResponseEntity<Jobs> getPublishedJobById(@PathVariable Integer id) {
        return service.getPublishedJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Jobs> getAll() {
        return service.getAll();
    }

    @GetMapping("/status/{status}")
    public List<Jobs> getByStatus(@PathVariable Jobs.Status status) {
        return service.getByStatus(status);
    }

    private Integer getCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var principal = (CustomUserDetails) auth.getPrincipal(); // Or however your principal is modeled
        return principal.getUserId(); // Adjust if your custom class is named differently
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jobs> updateJob(@PathVariable Integer id, @RequestBody Jobs updatedJob) {
        Integer currentUserId = getCurrentUserId();
        return ResponseEntity.ok(service.updateJob(id, updatedJob, currentUserId));
    }

}
