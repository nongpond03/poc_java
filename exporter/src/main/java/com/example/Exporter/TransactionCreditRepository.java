package com.example.Exporter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionCreditRepository extends JpaRepository<TransactionCredit, Long> {
    List<TransactionCredit> findByEffectiveDate(LocalDate effectiveDate);
}
