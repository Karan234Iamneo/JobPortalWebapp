package com.example.demo.repository;

import com.example.demo.entity.JobQuestions;
import com.example.demo.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JobQuestionsRepository extends JpaRepository<JobQuestions, Integer> {
    List<JobQuestions> findByJob(Jobs job);
    Optional<JobQuestions> findByJobAndQuestionNumber(Jobs job, Integer questionNumber);
}
