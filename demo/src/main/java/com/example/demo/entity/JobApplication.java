package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobApplicationId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Jobs job;

    @ManyToOne
    @JoinColumn(name = "jobseeker_id", nullable = false)
    private JobSeeker jobseeker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        applied, shortlisted, selected, rejected
    }
}
