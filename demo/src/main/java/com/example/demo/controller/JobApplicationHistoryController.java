package com.example.demo.controller;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.JobApplicationHistory;
import com.example.demo.service.JobApplicationHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application-history")
public class JobApplicationHistoryController {

    private final JobApplicationHistoryService service;

    public JobApplicationHistoryController(JobApplicationHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplicationHistory create(@RequestBody JobApplicationHistory history) {
        return service.logChange(history);
    }

    @GetMapping("/application/{id}")
    public List<JobApplicationHistory> getByApplication(@PathVariable JobApplication id) {
        return service.getByApplication(id);
    }
}
