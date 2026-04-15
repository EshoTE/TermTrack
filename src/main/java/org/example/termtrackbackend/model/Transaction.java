package org.example.termtrackbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private LocalDate date;
    private Double amount;
    private String category;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    public Transaction() {

    }


    public Transaction(Integer id, String description, LocalDate date, Double amount, String category, User user, String type) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.user = user;
        this.type = type;
    }


    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public String getCategory() {
        return category;
    }

    public String getType() { return type; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) { this.type = type; }
}
