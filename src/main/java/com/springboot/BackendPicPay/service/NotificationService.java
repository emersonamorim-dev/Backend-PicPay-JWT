package com.springboot.BackendPicPay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.springboot.BackendPicPay.model.User;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void notifyUser(User user) {
        // Simule a chamada de um serviço externo para notificar o usuário
        logger.info("Notifying user with ID: {}, Name: {}", user.getId(), user.getFullName());

    
    }
}
