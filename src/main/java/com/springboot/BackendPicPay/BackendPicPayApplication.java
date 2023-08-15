package com.springboot.BackendPicPay;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.BackendPicPay.model.Transaction;
import com.springboot.BackendPicPay.model.User;
import com.springboot.BackendPicPay.repository.TransactionRepository;
import com.springboot.BackendPicPay.repository.UserRepository;

@SpringBootApplication
public class BackendPicPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendPicPayApplication.class, args);
    }

    @Bean
    public CommandLineRunner setupDefaultUser(UserRepository userRepository, TransactionRepository transactionRepository) {
        return new InitialDataLoader(userRepository, transactionRepository);
    }

    @Component
    public static class InitialDataLoader implements CommandLineRunner {

        private final UserRepository userRepository;
        private final TransactionRepository transactionRepository;

        @Autowired
        public InitialDataLoader(UserRepository userRepository, TransactionRepository transactionRepository) {
            this.userRepository = userRepository;
            this.transactionRepository = transactionRepository;
        }

        @Override
        @Transactional
        public void run(String... args) throws Exception {
            // Create payer and payee
            User payer = new User();
            payer.setEmail("payer@example.com");
            payer.setFullName("Payer User");
            userRepository.save(payer);

            User payee = new User();
            payee.setEmail("payee@example.com");
            payee.setFullName("Emerson Amorim");
            userRepository.save(payee);

            // Create and save a transaction
            Transaction transaction = new Transaction();
            transaction.setValue(100.0);
            transaction.setPayer(payer);
            transaction.setPayee(payee);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepository.save(transaction);

            // Create and save another user
            User user = new User();
            user.setEmail("emerson.amorim@example.com");
            user.setFullName("Emerson Amorim");

            userRepository.save(user);
        }
    }
}

