package com.example.demo.controller;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.Jobs;
import com.example.demo.entity.JobSeeker;
import com.example.demo.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplication apply(@RequestBody JobApplication application) {
        return service.apply(application);
    }

    @GetMapping("/{id}")
    public Optional<JobApplication> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/job/{jobId}")
    public List<JobApplication> getByJob(@PathVariable Jobs jobId) {
        return service.getByJob(jobId);
    }

    @GetMapping("/jobseeker/{jobseekerId}")
    public List<JobApplication> getByJobseeker(@PathVariable JobSeeker jobseekerId) {
        return service.getByJobseeker(jobseekerId);
    }

    @GetMapping("/status/{status}")
    public List<JobApplication> getByStatus(@PathVariable JobApplication.Status status) {
        return service.getByStatus(status);
    }
}
