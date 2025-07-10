package com.example.demo.repository;

import com.example.demo.entity.JobQuestions;
import com.example.demo.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobQuestionsRepository extends JpaRepository<JobQuestions, Integer> {
    List<JobQuestions> findByJob(Jobs job);
}
