package org.example.termtrackbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


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
    private java.time.LocalDate date;
    private String label;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "term_plan_id")
    private TermPlan termPlan;



}
