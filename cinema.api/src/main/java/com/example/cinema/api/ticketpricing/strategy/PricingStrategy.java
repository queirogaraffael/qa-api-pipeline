package com.example.cinema.api.ticketpricing.strategy;

import com.example.cinema.api.entities.MovieSession;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(MovieSession session);
}
