package org.example.termtrackbackend.repository;

import org.example.termtrackbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {

}
