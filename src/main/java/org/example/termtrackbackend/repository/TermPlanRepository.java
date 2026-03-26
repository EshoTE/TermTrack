package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.TermPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermPlanRepository extends JpaRepository<TermPlan, Integer> {
}
