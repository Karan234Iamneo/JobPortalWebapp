package com.example.demo.controller;

import com.example.demo.entity.Jobs;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.JobsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

    private final JobsService service;
    private final JwtUtil jwtUtil;

    // ✅ Inject JwtUtil via constructor
    public JobsController(JobsService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public Jobs create(@RequestBody Jobs job) {
        return service.createJob(job);
    }

    @GetMapping("/{id}")
    public Optional<Jobs> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/status/published")
    public List<Jobs> getPublishedJobs() {
        return service.getPublishedJobs();
    }

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

    // ✅ Accept HttpServletRequest and use injected JwtUtil
    private Integer getCurrentUserId(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return jwtUtil.extractUserId(token);
        }
        return null; // or throw Unauthorized
    }

    // ✅ Pass request into getCurrentUserId
    @PutMapping("/{id}")
    public ResponseEntity<Jobs> updateJob(@PathVariable Integer id,
                                          @RequestBody Jobs updatedJob,
                                          HttpServletRequest request) {
        Integer currentUserId = getCurrentUserId(request);
        return ResponseEntity.ok(service.updateJob(id, updatedJob, currentUserId));
    }
}
