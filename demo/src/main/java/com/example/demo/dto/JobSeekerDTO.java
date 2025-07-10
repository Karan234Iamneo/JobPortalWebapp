package com.example.demo.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerDTO {
    private Integer jobseekerId;
    private String resumeUrl;
    private Integer userId;
    private List<String> skills;
    private List<String> jobRoleInterests;
}
