package com.example.cinema.api.pricing.context;

import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.pricing.strategy.PricingStrategy;

import java.math.BigDecimal;

public class PricingContext {
    private PricingStrategy strategy;

    public PricingContext(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public BigDecimal executeStrategy(MovieSession session) {
        return strategy.calculatePrice(session);
    }
}
