package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDTO {
    private Integer jobId;
    private String title;
    private String description;
    private String location;
    private Integer yearsOfExperience;
    private Double minSalary;
    private Double maxSalary;
    private List<String> skills;
    private String status;
    private String companyName;
}
