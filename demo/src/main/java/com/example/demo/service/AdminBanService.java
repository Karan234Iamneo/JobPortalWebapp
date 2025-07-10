package com.example.demo.service;

import com.example.demo.entity.AdminBan;
import com.example.demo.entity.User;
import com.example.demo.repository.AdminBanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBanService {

    private final AdminBanRepository repository;

    public AdminBanService(AdminBanRepository repository) {
        this.repository = repository;
    }

    public AdminBan ban(AdminBan ban) {
        return repository.save(ban);
    }

    public List<AdminBan> getByEntity(AdminBan.EntityType type, Integer entityId) {
        return repository.findByEntityTypeAndEntityId(type, entityId);
    }

    public List<AdminBan> getByStatus(AdminBan.Status status) {
        return repository.findByStatus(status);
    }

    public List<AdminBan> getByAdmin(User admin) {
        return repository.findByBannedBy(admin);
    }
}
