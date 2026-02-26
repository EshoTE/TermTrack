package org.example.termtrackbackend.model;

import jakarta.persistence.*;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double budget;
    private String category;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    public Budget() {}

    public Budget(Integer id, double budget, String category, User user) {
        this.id = id;
        this.budget = budget;
        this.category = category;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public double getBudget() {
        return budget;
    }
    public String getCategory() {
        return category;
    }
    public User getUser() {
        return user;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
