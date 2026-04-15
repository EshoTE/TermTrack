package org.example.termtrackbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TermPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String termName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalBudget;
    private String academicYear;
    private String yearOfStudy;
    private Double weeklyBudget;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "termPlan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Installment> installments;
}