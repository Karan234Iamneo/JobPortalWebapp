package com.example.demo.service;

import java.util.*;
import com.example.demo.repository.*;
import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;

public class RecruiterService {

    private final JobsRepository jobsRepo;
    private final JobApplicationRepository appRepo;
    private final JobApplicationAnswersRepository answersRepo;
    private final CompanyRepository companyRepo;

    public RecruiterService(JobsRepository jobsRepo,
                            JobApplicationRepository appRepo,
                            JobApplicationAnswersRepository answersRepo,
                            CompanyRepository companyRepo) {
        this.jobsRepo = jobsRepo;
        this.appRepo = appRepo;
        this.answersRepo = answersRepo;
        this.companyRepo = companyRepo;
    }

    public List<ApplicationReviewDTO> getApplicationsWithAnswersByJob(Integer jobId, User recruiter) {
        Jobs job = jobsRepo.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (!job.getEmployer().getUserId().equals(recruiter.getUserId())) {
            throw new AccessDeniedException("You do not own this job posting");
        }

        List<JobApplication> applications = appRepo.findByJob(job);

        return applications.stream().map(application -> {
            JobSeeker seeker = application.getJobseeker();
            List<JobApplicationAnswers> answers = answersRepo.findByJobApplication(application);

            List<ApplicationAnswerDTO> answerDTOs = answers.stream().map(ans -> new ApplicationAnswerDTO(
                    ans.getQuestion(), ans.getAnswer())).toList();

            return new ApplicationReviewDTO(
                    application.getId(),
                    seeker.getUser().getName(),
                    application.getStatus().toString(),
                    answerDTOs
            );
        }).toList();
    }

    public List<Jobs> getJobsPostedByRecruiter(User recruiter) {
        List<Company> companies = companyRepo.findByRecruiter(recruiter);
    
        if (companies.isEmpty()) {
            throw new ResourceNotFoundException("No companies found for this recruiter.");
        }
    
        return companies.stream()
                .flatMap(company -> jobsRepo.findByEmployer(company).stream())
                .toList();
    }
    

    public List<Company> getCompaniesManagedBy(User recruiter) {
        return companyRepo.findByRecruiter(recruiter);
    }
}