package org.example.termtrackbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TermPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String termName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalBudget;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TermPlan() {
    }

    public TermPlan(Integer id, String termName, LocalDate startDate, LocalDate endDate, User user, Double totalBudget) {
        this.id = id;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.totalBudget = totalBudget;
    }

    public Integer getId() {
        return id;
    }

    public String getTermName() {
        return termName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getTotalBudget() {
        return totalBudget;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTotalBudget(Double totalBudget) {
        this.totalBudget = totalBudget;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
