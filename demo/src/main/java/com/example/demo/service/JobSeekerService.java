package com.example.demo.service;

import com.example.demo.entity.JobSeeker;
import com.example.demo.entity.User;
import com.example.demo.repository.JobSeekerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerService {

    private final JobSeekerRepository jobSeekerRepository;

    public JobSeekerService(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        return jobSeekerRepository.save(jobSeeker);
    }

    public Optional<JobSeeker> getById(Integer id) {
        return jobSeekerRepository.findById(id);
    }

    public Optional<JobSeeker> getByUser(User user) {
        return jobSeekerRepository.findByUser(user);
    }

    public List<JobSeeker> getAll() {
        return jobSeekerRepository.findAll();
    }
}
