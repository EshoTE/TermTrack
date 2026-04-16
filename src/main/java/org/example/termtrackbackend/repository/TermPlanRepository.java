package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.TermPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermPlanRepository extends JpaRepository<TermPlan, Integer> {
    List<TermPlan> findByUserId(Integer userId);
}