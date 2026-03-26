package org.example.termtrackbackend.exception;

public class TermPlanNotFoundException extends RuntimeException {
    public TermPlanNotFoundException(Integer id) {
        super("Term plan with id " + id + " not found");
    }
}
