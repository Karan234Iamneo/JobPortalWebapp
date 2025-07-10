package com.example.demo.service;

import com.example.demo.entity.Jobs;
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
}
