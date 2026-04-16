package org.example.termtrackbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount;
    private LocalDate date;
    private String label;

    @ManyToOne
    @JoinColumn(name = "term_plan_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TermPlan termPlan;

    public Integer getTermPlanId() {
        return termPlan != null ? termPlan.getId() : null;
    }
}