package com.example.demo.dto;

import com.example.demo.entity.JobApplication.Status;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDTO {
    private Integer jobApplicationId;
    private Integer jobId;
    private Integer jobseekerId;
    private Status status;
}
