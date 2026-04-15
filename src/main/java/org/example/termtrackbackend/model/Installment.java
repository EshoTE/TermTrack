package org.example.termtrackbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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


    @ManyToOne
    @JoinColumn(name = "term_plan_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TermPlan termPlan;



}
