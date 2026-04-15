package org.example.termtrackbackend.exception;

public class InstallmentNotFoundException extends RuntimeException {
    public InstallmentNotFoundException(Integer id) {
        super("Installment with id " + id + " not found");
    }
}
