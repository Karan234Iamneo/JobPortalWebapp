package com.example.demo.service;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.JobApplicationHistory;
import com.example.demo.repository.JobApplicationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationHistoryService {

    private final JobApplicationHistoryRepository repository;

    public JobApplicationHistoryService(JobApplicationHistoryRepository repository) {
        this.repository = repository;
    }

    public JobApplicationHistory logChange(JobApplicationHistory history) {
        return repository.save(history);
    }

    public List<JobApplicationHistory> getByApplication(JobApplication application) {
        return repository.findByJobApplication(application);
    }
}
