package com.springboot.BackendPicPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.BackendPicPay.dto.TransactionDTO;
import com.springboot.BackendPicPay.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // 1. Criar uma transação 
    @PostMapping
    public ResponseEntity<Void> makeTransaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.makeTransaction(transactionDTO);
        return ResponseEntity.ok().build();
    }

    // 2. Obter uma transação por ID
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    // 3. Listar todas as transações
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    // 4. Atualizar uma transação 
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransaction(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO) {
        transactionService.updateTransaction(id, transactionDTO);
        return ResponseEntity.ok().build();
    }

    // 5. Excluir uma transação 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}

