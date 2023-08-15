package com.springboot.BackendPicPay;

import com.springboot.BackendPicPay.repository.TransactionRepository;
import com.springboot.BackendPicPay.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class InitialDataLoaderTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private BackendPicPayApplication.InitialDataLoader initialDataLoader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRun() throws Exception {
        initialDataLoader.run();

        verify(userRepository).save(org.mockito.ArgumentMatchers.any());
        verify(transactionRepository).save(org.mockito.ArgumentMatchers.any());
    }
}
