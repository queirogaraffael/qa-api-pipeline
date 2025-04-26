package com.example.cinema.api.ticketpricing.strategy;

import com.example.cinema.api.entities.MovieSession;

import java.math.BigDecimal;

public class RegularPricing implements PricingStrategy {
    public BigDecimal calculatePrice(MovieSession session) {
        return session.getBasePrice();
    }
}


