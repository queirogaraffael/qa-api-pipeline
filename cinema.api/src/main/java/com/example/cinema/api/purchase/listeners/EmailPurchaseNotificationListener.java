package com.example.cinema.api.purchase.listeners;

import com.example.cinema.api.entities.Purchase;
import com.example.cinema.api.purchase.event.PurchaseCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailPurchaseNotificationListener {

    @EventListener
    public void handlePurchase(PurchaseCreatedEvent event) {
        Purchase purchase = event.getPurchase();
        //System.out.println("📧 Enviando e-mail para " + purchase.getUser().getEmail());
    }
}
