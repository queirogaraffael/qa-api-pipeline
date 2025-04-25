package com.example.cinema.api.pricing.strategy;

import com.example.cinema.api.entities.MovieSession;

import java.math.BigDecimal;

public class SeniorPricing implements PricingStrategy {
    public BigDecimal calculatePrice(MovieSession session) {
        return session.getBasePrice().multiply(new BigDecimal("0.7"));
    }
}
