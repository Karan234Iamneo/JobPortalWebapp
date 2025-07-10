package com.example.demo.dto;

import com.example.demo.entity.Jobs.Status;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsDTO {
    private Integer jobId;
    private String description;
    private Integer employerId;
    private String location;
    private Integer yearsOfExperience;
    private Double minSalary;
    private Double maxSalary;
    private List<String> skills;
    private Status status;
}
