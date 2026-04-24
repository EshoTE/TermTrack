package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.config.JwtService;
import org.example.termtrackbackend.model.*;
import org.example.termtrackbackend.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final TermPlanRepository termPlanRepository;
    private final InstallmentRepository installmentRepository;
    private final BudgetRepository budgetRepository;
    private final JwtService jwtService;

    public AdminController(UserRepository userRepository,
                           TransactionRepository transactionRepository,
                           TermPlanRepository termPlanRepository,
                           InstallmentRepository installmentRepository,
                           BudgetRepository budgetRepository,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.termPlanRepository = termPlanRepository;
        this.installmentRepository = installmentRepository;
        this.budgetRepository = budgetRepository;
        this.jwtService = jwtService;
    }

    private boolean isAdmin(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        return user.getRole() == Role.ADMIN;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Access denied");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getAllTransactions(HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Access denied");
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    @GetMapping("/termplans")
    public ResponseEntity<?> getAllTermPlans(HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Access denied");
        return ResponseEntity.ok(termPlanRepository.findAll());
    }

    @GetMapping("/installments")
    public ResponseEntity<?> getAllInstallments(HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Access denied");
        return ResponseEntity.ok(installmentRepository.findAll());
    }

    @GetMapping("/budgets")
    public ResponseEntity<?> getAllBudgets(HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Access denied");
        return ResponseEntity.ok(budgetRepository.findAll());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id, HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Access denied");
        if (!userRepository.existsById(id)) return ResponseEntity.status(404).body("User not found");
        userRepository.deleteById(id);
        return ResponseEntity.ok("User deleted");
    }
}