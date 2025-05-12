package com.example.cinema.api.services;

import com.example.cinema.api.repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final TicketService ticketService;

    public PurchaseService(PurchaseRepository purchaseRepository, TicketService ticketService) {
        this.purchaseRepository = purchaseRepository;
        this.ticketService = ticketService;
    }


    // TODO: Criar compra
    // Recebe uma compra dto
    // Valida se o ticket é valido
    // Valida se MovieSession é valido
    // Aplica desconto com ticketPricingContext
    // salva a compra
    // Notifica o usuario




}
