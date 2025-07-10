package com.example.demo.controller;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.JobApplicationAnswers;
import com.example.demo.service.JobApplicationAnswersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application-answers")
public class JobApplicationAnswersController {

    private final JobApplicationAnswersService service;

    public JobApplicationAnswersController(JobApplicationAnswersService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplicationAnswers save(@RequestBody JobApplicationAnswers answer) {
        return service.saveAnswer(answer);
    }

    @GetMapping("/application/{appId}")
    public List<JobApplicationAnswers> getByApp(@PathVariable JobApplication appId) {
        return service.getByApplication(appId);
    }
}
