package com.example.demo.service;

import com.example.demo.entity.JobSeeker;
import com.example.demo.repository.JobSeekerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {

    private final JobSeekerRepository repository;

    public JobSeekerService(JobSeekerRepository repository) {
        this.repository = repository;
    }

    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        return repository.save(jobSeeker);
    }

    public Optional<JobSeeker> getById(Integer id) {
        return repository.findById(id);
    }

    public List<JobSeeker> getAll() {
        return repository.findAll();
    }

    public Optional<JobSeeker> getByUserId(Integer userId) {
        return repository.findByUserUserId(userId);
    }

    public JobSeeker updateJobSeeker(Integer id, JobSeeker updatedJobSeeker) {
        return repository.findById(id).map(existing -> {
            existing.setResumeUrl(updatedJobSeeker.getResumeUrl());
            existing.setSkills(updatedJobSeeker.getSkills());
            existing.setJobRoleInterests(updatedJobSeeker.getJobRoleInterests());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("JobSeeker not found with ID " + id));
    }
}
