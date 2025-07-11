package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String Title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Company employer;

    @Column(length = 100)
    private String location;

    private Integer yearsOfExperience;

    private Double minSalary;

    private Double maxSalary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ElementCollection
    private List<String> skills;

    public enum Status {
        draft, published, closed, cancelled
    }
}
