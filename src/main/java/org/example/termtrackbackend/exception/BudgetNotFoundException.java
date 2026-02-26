package org.example.termtrackbackend.exception;

public class BudgetNotFoundException extends RuntimeException {
    public BudgetNotFoundException(Integer id) {
        super("Could not find budget with id " + id);
    }
}
