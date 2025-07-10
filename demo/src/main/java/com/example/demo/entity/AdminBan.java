package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdminBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer banId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EntityType entityType;

    @Column(nullable = false)
    private Integer entityId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "banned_by", nullable = false)
    private User bannedBy;

    @Column(nullable = false)
    private Date bannedOn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.active;

    public enum EntityType {
        job, company, jobseeker
    }

    public enum Status {
        active, revoked
    }
}
