package com.example.demo.service;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.JobApplicationAnswers;
import com.example.demo.repository.JobApplicationAnswersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationAnswersService {

    private final JobApplicationAnswersRepository repository;

    public JobApplicationAnswersService(JobApplicationAnswersRepository repository) {
        this.repository = repository;
    }

    public JobApplicationAnswers saveAnswer(JobApplicationAnswers answer) {
        return repository.save(answer);
    }

    public List<JobApplicationAnswers> getByApplication(JobApplication application) {
        return repository.findByJobApplication(application);
    }
}
