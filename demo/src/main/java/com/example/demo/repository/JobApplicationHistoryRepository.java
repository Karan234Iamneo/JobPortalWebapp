package com.example.demo.repository;

import com.example.demo.entity.JobApplicationHistory;
import com.example.demo.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationHistoryRepository extends JpaRepository<JobApplicationHistory, Integer> {
    List<JobApplicationHistory> findByJobApplication(JobApplication jobApplication);
}
