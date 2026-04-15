package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.exception.TermPlanNotFoundException;
import org.example.termtrackbackend.model.TermPlan;
import org.example.termtrackbackend.repository.TermPlanRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TermPlanController {
    private final TermPlanRepository termPlanRepository;

    public TermPlanController(TermPlanRepository termPlanRepository) {
        this.termPlanRepository = termPlanRepository;
    }

    @GetMapping("/termPlans")
    public List<TermPlan> getAllTermPlans() {
        return termPlanRepository.findAll();
    }

    @GetMapping("/termPlan/{id}")
    public TermPlan getTermPlanById(@PathVariable Integer id) {
        return termPlanRepository.findById(id)
                .orElseThrow(() -> new TermPlanNotFoundException(id));
    }

    @PostMapping("/termPlan")
    public TermPlan createTermPlan(@RequestBody TermPlan termPlan) {
        return termPlanRepository.save(termPlan);
    }

    @PutMapping("/termPlan/{id}")
    public TermPlan updateTermPlan(@PathVariable Integer id, @RequestBody TermPlan termPlan) {
        return termPlanRepository.findById(id)
                .map( termPlan1 -> {
                    termPlan1.setTermName(termPlan.getTermName());
                    termPlan1.setEndDate(termPlan.getEndDate());
                    termPlan1.setStartDate(termPlan.getStartDate());
                    termPlan1.setTotalBudget(termPlan.getTotalBudget());
                    termPlan1.setAcademicYear(termPlan.getAcademicYear());
                    termPlan1.setYearOfStudy(termPlan.getYearOfStudy());
                    termPlan1.setWeeklyBudget(termPlan.getWeeklyBudget());
                    return termPlanRepository.save(termPlan1);
                }).orElseThrow(() -> new TermPlanNotFoundException(id));
    }

    @DeleteMapping("/termPlan/{id}")
    public void deleteTermPlan(@PathVariable Integer id) {
        if (!termPlanRepository.existsById(id)) {
            throw new TermPlanNotFoundException(id);
        }
        termPlanRepository.deleteById(id);
    }
}
