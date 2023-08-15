package com.springboot.BackendPicPay.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.BackendPicPay.dto.TransactionDTO;
import com.springboot.BackendPicPay.model.Transaction;
import com.springboot.BackendPicPay.model.User;
import com.springboot.BackendPicPay.model.UserType;
import com.springboot.BackendPicPay.repository.TransactionRepository;
import com.springboot.BackendPicPay.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private NotificationService notificationService;

    public void makeTransaction(TransactionDTO transactionDTO) {
        User payer = userRepository.findById(transactionDTO.getPayerId()).orElseThrow(() -> new RuntimeException("Payer not found"));
        User payee = userRepository.findById(transactionDTO.getPayeeId()).orElseThrow(() -> new RuntimeException("Payee not found"));

        if(payer.getBalance() < transactionDTO.getValue()) {
            throw new RuntimeException("Insufficient funds");
        }

        if(payer.getUserType() == UserType.STORE) {
            throw new RuntimeException("Stores cannot make transactions");
        }

        payer.setBalance(payer.getBalance() - transactionDTO.getValue());
        payee.setBalance(payee.getBalance() + transactionDTO.getValue());

        Transaction transaction = new Transaction();
        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setValue(transactionDTO.getValue());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);

        notificationService.notifyUser(payee);
    }


    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        return convertToDTO(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        // Aqui, você pode atualizar os campos necessários da transação
        // Por exemplo:
        transaction.setValue(transactionDTO.getValue());
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setPayerId(transaction.getPayer().getId());
        dto.setPayeeId(transaction.getPayee().getId());
        dto.setValue(transaction.getValue());
        // Adicione outros campos conforme necessário
        return dto;
    }
}

