package com.springboot.BackendPicPay.dto;

public class TransactionDTO {
    private Long payerId;
    private Long payeeId;
    private Double value;

    // Constructors
    public TransactionDTO() {}

    public TransactionDTO(Long payerId, Long payeeId, Double value) {
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.value = value;
    }

    // Getters and Setters
    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "payerId=" + payerId +
                ", payeeId=" + payeeId +
                ", value=" + value +
                '}';
    }
}

