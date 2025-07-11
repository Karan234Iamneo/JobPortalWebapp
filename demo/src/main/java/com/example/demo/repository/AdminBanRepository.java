package com.example.demo.repository;

import com.example.demo.entity.AdminBan;
import com.example.demo.entity.User;
import com.example.demo.entity.AdminBan.EntityType;
import com.example.demo.entity.AdminBan.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdminBanRepository extends JpaRepository<AdminBan, Integer> {
    List<AdminBan> findByEntityTypeAndEntityId(EntityType entityType, Integer entityId);
    List<AdminBan> findByStatus(Status status);
    List<AdminBan> findByBannedBy(User bannedBy);
    List<AdminBan> findByEntityTypeAndEntityIdAndStatus(EntityType type, Integer id, Status status);
}
