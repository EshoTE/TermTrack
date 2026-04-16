package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.config.JwtService;
import org.example.termtrackbackend.exception.TransactionNotFoundException;
import org.example.termtrackbackend.model.Transaction;
import org.example.termtrackbackend.repository.TransactionRepository;
import org.example.termtrackbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository, JwtService jwtService, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    private Integer getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email).orElseThrow().getId();
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(HttpServletRequest request) {
        Integer userId = getUserIdFromRequest(request);
        return transactionRepository.findByUserId(userId);
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
                }).orElseThrow(() -> new TransactionNotFoundException(id));
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