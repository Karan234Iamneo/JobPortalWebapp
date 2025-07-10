package com.example.demo.controller;

import com.example.demo.entity.Jobs;
import com.example.demo.service.JobsService;
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

    @GetMapping
    public List<Jobs> getAll() {
        return service.getAll();
    }

    @GetMapping("/status/{status}")
    public List<Jobs> getByStatus(@PathVariable Jobs.Status status) {
        return service.getByStatus(status);
    }
}
