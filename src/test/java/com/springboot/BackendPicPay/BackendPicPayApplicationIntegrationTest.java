package com.springboot.BackendPicPay;

import com.springboot.BackendPicPay.model.User;
import com.springboot.BackendPicPay.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

@SpringBootTest
public class BackendPicPayApplicationIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testInitialDataLoading() {
        Optional<User> payer = userRepository.findByEmail("payer@example.com");
        assertNotNull(payer);

        Optional<User> payee = userRepository.findByEmail("payee@example.com");
        assertNotNull(payee);
    }
}
