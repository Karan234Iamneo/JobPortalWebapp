package com.example.demo.service;

import com.example.demo.entity.JobQuestions;
import com.example.demo.entity.Jobs;
import com.example.demo.repository.JobQuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobQuestionsService {

    private final JobQuestionsRepository repository;

    public JobQuestionsService(JobQuestionsRepository repository) {
        this.repository = repository;
    }

    public JobQuestions addQuestion(JobQuestions question) {
        return repository.save(question);
    }

    public List<JobQuestions> getByJob(Jobs job) {
        return repository.findByJob(job);
    }
}
