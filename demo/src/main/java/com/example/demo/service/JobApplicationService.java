package com.example.demo.service;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.Jobs;
import com.example.demo.entity.JobSeeker;
import com.example.demo.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    public JobApplication apply(JobApplication application) {
        return repository.save(application);
    }

    public Optional<JobApplication> getById(Integer id) {
        return repository.findById(id);
    }

    public List<JobApplication> getByJob(Jobs job) {
        return repository.findByJob(job);
    }

    public List<JobApplication> getByJobseeker(JobSeeker seeker) {
        return repository.findByJobseeker(seeker);
    }

    public List<JobApplication> getByStatus(JobApplication.Status status) {
        return repository.findByStatus(status);
    }
}
