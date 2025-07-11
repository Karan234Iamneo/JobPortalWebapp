package com.example.demo.dto;

import lombok.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationReviewDTO {
    private Integer applicationId;
    private String jobSeekerName;
    private String status;
    private List<ApplicationAnswerDTO> answers;
}
