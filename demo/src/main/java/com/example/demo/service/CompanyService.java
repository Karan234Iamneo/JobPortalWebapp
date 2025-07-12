package com.example.demo.service;

import com.example.demo.entity.Company;
import com.example.demo.entity.User;
import com.example.demo.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> getById(Integer id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> getByRecruiter(User user) {
        return companyRepository.findByRecruiter(user);
    }

    public List<Company> getByStatus(Company.Status status) {
        return companyRepository.findByStatus(status);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }
}
