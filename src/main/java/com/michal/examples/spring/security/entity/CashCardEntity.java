package com.michal.examples.spring.security.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cash_card")
public class CashCardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner")
    private String owner;

    @Column(name = "amount")
    private Double amount;

    public CashCardEntity() {
        // JPA requirement
    }

    public CashCardEntity(Long id, Double amount, String owner) {
        this.id = id;
        this.owner = owner;
        this.amount = amount;
    }

    public CashCardEntity(Double amount, String owner) {
        this.owner = owner;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashCardEntity that = (CashCardEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
