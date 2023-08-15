package com.springboot.BackendPicPay.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    @ManyToOne
    private User payer;
    @ManyToOne
    private User payee;
    private LocalDateTime timestamp;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
    public User getPayer() {
        return payer;
    }
    public void setPayer(User payer) {
        this.payer = payer;
    }
    public User getPayee() {
        return payee;
    }
    public void setPayee(User payee) {
        this.payee = payee;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
