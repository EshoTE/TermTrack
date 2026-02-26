package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
