package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<Company> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Company> getAll() {
        return service.getAll();
    }

    @GetMapping("/status/{status}")
    public List<Company> getByStatus(@PathVariable Company.Status status) {
        return service.getByStatus(status);
    }
}
