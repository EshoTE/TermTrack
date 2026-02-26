package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.exception.TransactionNotFoundException;
import org.example.termtrackbackend.exception.UserNotFoundException;
import org.example.termtrackbackend.model.Transaction;
import org.example.termtrackbackend.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transaction/{id}")
    public Transaction getTransactionById(@PathVariable Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/transaction/{id}")
    public Transaction updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        return transactionRepository.findById(id)
                .map(transaction1 -> {
                    transaction1.setAmount(transaction.getAmount());
                    transaction1.setCategory(transaction.getCategory());
                    transaction1.setDescription(transaction.getDescription());
                    transaction1.setDate(transaction.getDate());
                    return transactionRepository.save(transaction1);
                }).orElseThrow(()-> new TransactionNotFoundException(id));
    }

    @DeleteMapping("/transaction/{id}")
    public String deleteTransaction(@PathVariable Integer id) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException(id);
        }
        transactionRepository.deleteById(id);
        return "Transaction deleted";
    }

}
