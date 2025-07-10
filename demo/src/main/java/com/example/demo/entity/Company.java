package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;

    @Column(nullable = false, unique = true, length = 100)
    private String companyName;

    @Column(length = 100)
    private String companyLocation;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;

    @Column(columnDefinition = "TEXT")
    private String about;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.in_review;

    public enum Status {
        in_review, published, banned
    }
}
