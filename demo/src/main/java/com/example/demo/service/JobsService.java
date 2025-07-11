package com.example.demo.service;

import com.example.demo.entity.Jobs;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.Company;
import com.example.demo.repository.JobsRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobsService {

    private final JobsRepository jobsRepository;

    public JobsService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public Jobs createJob(Jobs job) {
        return jobsRepository.save(job);
    }

    // For listing only published jobs publicly
    public List<Jobs> getPublishedJobs() {
        return jobsRepository.findByStatus(Jobs.Status.published);
    }

    public Optional<Jobs> getPublishedJobById(Integer id) {
        return jobsRepository.findByIdAndStatus(id, Jobs.Status.published);
    }

    public Optional<Jobs> getById(Integer id) {
        return jobsRepository.findById(id);
    }

    public List<Jobs> getByEmployer(Company company) {
        return jobsRepository.findByEmployer(company);
    }

    public List<Jobs> getByStatus(Jobs.Status status) {
        return jobsRepository.findByStatus(status);
    }

    public List<Jobs> getAll() {
        return jobsRepository.findAll();
    }

    public Jobs updateJob(Integer id, Jobs updatedJob) {
        Jobs existing = jobsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));

        // Update all relevant fields
        existing.setTitle(updatedJob.getTitle());
        existing.setDescription(updatedJob.getDescription());
        existing.setLocation(updatedJob.getLocation());
        existing.setYearsOfExperience(updatedJob.getYearsOfExperience());
        existing.setMinSalary(updatedJob.getMinSalary());
        existing.setMaxSalary(updatedJob.getMaxSalary());
        existing.setStatus(updatedJob.getStatus());
        existing.setSkills(updatedJob.getSkills());

        return jobsRepository.save(existing);
    }

}
