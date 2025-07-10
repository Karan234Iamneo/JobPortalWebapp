package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobseekerId;

    @Column(length = 255)
    private String resumeUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<String> jobRoleInterests;
}
