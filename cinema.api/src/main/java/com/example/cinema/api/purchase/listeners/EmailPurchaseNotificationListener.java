package com.example.cinema.api.purchase.listeners;

import com.example.cinema.api.entities.Purchase;
import com.example.cinema.api.purchase.event.PurchaseCreatedEvent;
import com.example.cinema.api.services.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailPurchaseNotificationListener {

    private final EmailService emailService;

    public EmailPurchaseNotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    public void handlePurchase(PurchaseCreatedEvent event) {
        Purchase purchase = event.getPurchase();

        emailService.sendPurchaseNotificationEmail(purchase.getUser().getEmail(), purchase);
    }
}
