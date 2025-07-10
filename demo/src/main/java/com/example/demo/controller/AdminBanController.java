package com.example.demo.controller;

import com.example.demo.entity.AdminBan;
import com.example.demo.entity.User;
import com.example.demo.service.AdminBanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bans")
public class AdminBanController {

    private final AdminBanService service;

    public AdminBanController(AdminBanService service) {
        this.service = service;
    }

    @PostMapping
    public AdminBan ban(@RequestBody AdminBan ban) {
        return service.ban(ban);
    }

    @GetMapping("/entity")
    public List<AdminBan> getByEntity(@RequestParam AdminBan.EntityType type, @RequestParam Integer id) {
        return service.getByEntity(type, id);
    }

    @GetMapping("/admin/{adminId}")
    public List<AdminBan> getByAdmin(@PathVariable User adminId) {
        return service.getByAdmin(adminId);
    }

    @GetMapping("/status/{status}")
    public List<AdminBan> getByStatus(@PathVariable AdminBan.Status status) {
        return service.getByStatus(status);
    }
}
