package com.example.demo.repository;

import com.example.demo.entity.JobApplicationAnswers;
import com.example.demo.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationAnswersRepository extends JpaRepository<JobApplicationAnswers, Integer> {
    List<JobApplicationAnswers> findByJobApplication(JobApplication jobApplication);
}
