package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.exception.InstallmentNotFoundException;
import org.example.termtrackbackend.model.Installment;
import org.example.termtrackbackend.repository.InstallmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InstallmentController {

    private final InstallmentRepository installmentRepository;

    public InstallmentController(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @GetMapping("/installments")
    public List<Installment> getInstallments() { return installmentRepository.findAll(); }

    @GetMapping("/installment/{id}")
    public Installment getInstallmentById(@PathVariable Integer id) {
        return installmentRepository.findById(id)
                    .orElseThrow(() -> new InstallmentNotFoundException(id)); }


    @PostMapping("/installment")
    public Installment createInstallment(@RequestBody Installment installment) {
        return installmentRepository.save(installment);
    }

    @PutMapping("/installment/{id}")
    public Installment updateInstallment(@PathVariable Integer id, @RequestBody Installment installment) {
        return installmentRepository.findById(id)
                .map(installment1 -> {
                    installment1.setAmount(installment.getAmount());
                    installment1.setDate(installment.getDate());
                    installment1.setLabel(installment.getLabel());
                    return installmentRepository.save(installment1);
                }).orElseThrow(() -> new InstallmentNotFoundException(id));
    }

    @DeleteMapping("/installment/{id}")
    public void deleteInstallment(@PathVariable Integer id) {
        if (!installmentRepository.existsById(id)) {
            throw new InstallmentNotFoundException(id);
        }
        installmentRepository.deleteById(id);
    }
}
