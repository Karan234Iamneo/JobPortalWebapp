package com.example.demo.service;

import com.example.demo.entity.Jobs;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.Company;
import com.example.demo.repository.JobsRepository;

import jakarta.persistence.EntityNotFoundException;

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
        return jobsRepository.findByJobIdAndStatus(id, Jobs.Status.published);
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

    public Jobs updateJob(Integer id, Jobs updatedJob, Integer currentUserId) {
        return jobsRepository.findById(id).map(existing -> {
            if (!existing.getEmployer().getRecruiter().getUserId().equals(currentUserId)) {
                throw new AccessDeniedException("You are not the owner of this job post.");
            }

            // Copy allowed updatable fields
            existing.setTitle(updatedJob.getTitle());
            existing.setDescription(updatedJob.getDescription());
            existing.setLocation(updatedJob.getLocation());
            existing.setYearsOfExperience(updatedJob.getYearsOfExperience());
            existing.setMinSalary(updatedJob.getMinSalary());
            existing.setMaxSalary(updatedJob.getMaxSalary());
            existing.setSkills(updatedJob.getSkills());
            existing.setStatus(updatedJob.getStatus());

            return jobsRepository.save(existing);
        }).orElseThrow(() -> new EntityNotFoundException("Job not found"));
    }

}
