package com.example.demo.service;

import com.example.demo.entity.Jobs;
import com.example.demo.entity.Company;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.repository.CompanyRepository;
import com.example.demo.repository.JobsRepository;
import com.example.demo.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobsService {

    private final JobsRepository jobsRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public JobsService(JobsRepository jobsRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.jobsRepository = jobsRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }

    // ✅ Use currentUserId to fetch employer
    public Jobs createJob(Jobs job, Integer currentUserId) {
        Company employer = companyRepository.findByRecruiter(userRepository.getById(currentUserId))
                .orElseThrow(() -> new EntityNotFoundException(
                    "Company profile not found for user ID " + currentUserId));


        job.setEmployer(employer); // override whatever the user might have passed
        return jobsRepository.save(job);
    }

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
