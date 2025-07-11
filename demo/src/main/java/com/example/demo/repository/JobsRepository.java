package com.example.demo.repository;

import com.example.demo.entity.Jobs;
import com.example.demo.entity.Company;
import com.example.demo.entity.Jobs.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface JobsRepository extends JpaRepository<Jobs, Integer> {
    List<Jobs> findByEmployer(Company employer);
    List<Jobs> findByStatus(Status status);
    Optional<Jobs> findByIdAndStatus(Integer id, Jobs.Status status);
}
