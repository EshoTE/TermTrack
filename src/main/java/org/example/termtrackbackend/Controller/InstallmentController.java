package org.example.termtrackbackend.Controller;

import org.example.termtrackbackend.config.JwtService;
import org.example.termtrackbackend.exception.InstallmentNotFoundException;
import org.example.termtrackbackend.model.Installment;
import org.example.termtrackbackend.repository.InstallmentRepository;
import org.example.termtrackbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InstallmentController {

    private final InstallmentRepository installmentRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public InstallmentController(InstallmentRepository installmentRepository, JwtService jwtService, UserRepository userRepository) {
        this.installmentRepository = installmentRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    private Integer getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email).orElseThrow().getId();
    }

    @GetMapping("/installments")
    public List<Installment> getInstallments(HttpServletRequest request) {
        Integer userId = getUserIdFromRequest(request);
        return installmentRepository.findByTermPlanUserId(userId);
    }

    @GetMapping("/installment/{id}")
    public Installment getInstallmentById(@PathVariable Integer id) {
        return installmentRepository.findById(id)
                .orElseThrow(() -> new InstallmentNotFoundException(id));
    }

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
    public String deleteInstallment(@PathVariable Integer id) {
        if (!installmentRepository.existsById(id)) {
            throw new InstallmentNotFoundException(id);
        }
        installmentRepository.deleteById(id);
        return "Installment deleted";
    }
}