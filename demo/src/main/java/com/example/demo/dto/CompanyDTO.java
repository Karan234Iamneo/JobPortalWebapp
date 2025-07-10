package com.example.demo.dto;

import com.example.demo.entity.Company.Status;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private Integer companyId;
    private String companyName;
    private String companyLocation;
    private Integer recruiterId;
    private String about;
    private Status status;
}
