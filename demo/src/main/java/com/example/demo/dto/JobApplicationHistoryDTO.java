package com.example.demo.dto;

import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationHistoryDTO {
    private Integer jobApplicationHistoryId;
    private Integer jobApplicationId;
    private Date changedOn;
    private Integer changedBy;
    private String previous;
    private String current;
}
