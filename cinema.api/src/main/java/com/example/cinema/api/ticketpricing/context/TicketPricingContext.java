package com.example.cinema.api.ticketpricing.context;

import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.ticketpricing.strategy.PricingStrategy;

import java.math.BigDecimal;

public class TicketPricingContext {
    private PricingStrategy strategy;

    public TicketPricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal executeStrategy(MovieSession session) {
        return strategy.calculatePrice(session);
    }
}
