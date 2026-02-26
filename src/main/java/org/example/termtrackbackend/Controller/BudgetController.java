package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.exception.BudgetNotFoundException;
import org.example.termtrackbackend.model.Budget;
import org.example.termtrackbackend.repository.BudgetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BudgetController {

    private final BudgetRepository budgetRepository;

    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @GetMapping("/budgets")
    public List<Budget> getBudgets() {
        return budgetRepository.findAll();
    }

    @GetMapping("/budget/{id}")
    public Budget getBudgetById(@PathVariable Integer id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new BudgetNotFoundException(id));
    }

    @PostMapping("/budget")
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    @PutMapping("/budget/{id}")
    public Budget updateBudget(@PathVariable Integer id, @RequestBody Budget budget) {
        return budgetRepository.findById(id)
                .map(budget1 -> {
                    budget1.setBudget(budget.getBudget());
                    budget1.setCategory(budget.getCategory());
                    return budgetRepository.save(budget1);
                }).orElseThrow(() -> new BudgetNotFoundException(id));
    }

    @DeleteMapping("/budget/{id}")
    public String deleteBudget(@PathVariable Integer id) {
        if (!budgetRepository.existsById(id)) {
            throw new BudgetNotFoundException(id);
        }
        budgetRepository.deleteById(id);
        return "Budget Deleted";
    }

}
