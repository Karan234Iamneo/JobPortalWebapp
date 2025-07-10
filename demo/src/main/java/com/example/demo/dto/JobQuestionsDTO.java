package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobQuestionsDTO {
    private Integer questionId;
    private Integer jobId;
    private String question;
}
