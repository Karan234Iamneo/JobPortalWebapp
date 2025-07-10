package com.example.demo.repository;

import com.example.demo.entity.JobApplication;
import com.example.demo.entity.Jobs;
import com.example.demo.entity.JobSeeker;
import com.example.demo.entity.JobApplication.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {
    List<JobApplication> findByJob(Jobs job);
    List<JobApplication> findByJobseeker(JobSeeker jobSeeker);
    List<JobApplication> findByStatus(Status status);
}
