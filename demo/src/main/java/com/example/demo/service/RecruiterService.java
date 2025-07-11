package com.example.demo.service;

import com.example.demo.dto.ApplicationAnswerDTO;
import com.example.demo.dto.ApplicationReviewDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.AccessDeniedException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterService {

    private final JobsRepository jobsRepo;
    private final JobApplicationRepository appRepo;
    private final JobApplicationAnswersRepository answersRepo;
    private final JobQuestionsRepository questionRepo;

    public RecruiterService(
            JobsRepository jobsRepo,
            JobApplicationRepository appRepo,
            JobApplicationAnswersRepository answersRepo,
            JobQuestionsRepository questionRepo
    ) {
        this.jobsRepo = jobsRepo;
        this.appRepo = appRepo;
        this.answersRepo = answersRepo;
        this.questionRepo = questionRepo;
    }

    public List<ApplicationReviewDTO> getApplicationsWithAnswersByJob(Integer jobId, User recruiter) {
        Jobs job = jobsRepo.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (!job.getEmployer().getRecruiter().getUserId().equals(recruiter.getUserId())) {
            throw new AccessDeniedException("You do not own this job posting");
        }

        List<JobApplication> applications = appRepo.findByJob(job);

        return applications.stream().map(application -> {
            JobSeeker seeker = application.getJobseeker();
            List<JobApplicationAnswers> answers = answersRepo.findByJobApplication(application);

            List<ApplicationAnswerDTO> answerDTOs = answers.stream().map(ans -> {
                JobQuestions question = questionRepo
                        .findByJobAndQuestionNumber(job, ans.getQuestionNumber())
                        .orElseThrow(() -> new ResourceNotFoundException("Question not found for number: " + ans.getQuestionNumber()));

                return new ApplicationAnswerDTO(question.getQuestion(), ans.getAnswer());
            }).toList();

            return new ApplicationReviewDTO(
                    application.getJobApplicationId(),
                    seeker.getUser().getName(),
                    application.getStatus().toString(),
                    answerDTOs
            );
        }).toList();
    }
}
