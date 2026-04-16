package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Integer> {
    List<Installment> findByTermPlanUserId(Integer userId);
}