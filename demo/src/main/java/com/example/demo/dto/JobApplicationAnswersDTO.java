package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationAnswersDTO {
    private Integer answerId;
    private Integer jobApplicationId;
    private Integer questionNumber;
    private String answer;
}
