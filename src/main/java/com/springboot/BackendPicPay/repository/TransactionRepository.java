package com.springboot.BackendPicPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.BackendPicPay.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}
