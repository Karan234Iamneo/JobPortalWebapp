package com.example.demo.dto;

import com.example.demo.entity.AdminBan.EntityType;
import com.example.demo.entity.AdminBan.Status;
import lombok.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminBanDTO {
    private Integer banId;
    private EntityType entityType;
    private Integer entityId;
    private String reason;
    private Integer bannedBy;
    private Date bannedOn;
    private Status status;
}
