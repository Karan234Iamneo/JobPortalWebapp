package com.example.demo.repository;

import com.example.demo.entity.Company;
import com.example.demo.entity.User;
import com.example.demo.entity.Company.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<Company> findByRecruiter(User recruiter);
    List<Company> findByStatus(Status status);
}
