package com.example.demo.repo;

import com.example.demo.entity.RunningNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunningNumberRepository extends JpaRepository<RunningNumber, Long> {
}