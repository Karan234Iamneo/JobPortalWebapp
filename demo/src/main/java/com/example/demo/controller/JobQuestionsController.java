package com.example.demo.controller;

import com.example.demo.entity.JobQuestions;
import com.example.demo.entity.Jobs;
import com.example.demo.service.JobQuestionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-questions")
public class JobQuestionsController {

    private final JobQuestionsService service;

    public JobQuestionsController(JobQuestionsService service) {
        this.service = service;
    }

    @PostMapping
    public JobQuestions create(@RequestBody JobQuestions question) {
        return service.addQuestion(question);
    }

    @GetMapping("/job/{jobId}")
    public List<JobQuestions> getByJob(@PathVariable Jobs jobId) {
        return service.getByJob(jobId);
    }
}
