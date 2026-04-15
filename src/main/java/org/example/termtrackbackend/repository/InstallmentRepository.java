package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Integer> {
}
