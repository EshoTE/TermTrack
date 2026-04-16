package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByUserId(Integer userId);
}