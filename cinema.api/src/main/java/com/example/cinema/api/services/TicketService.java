package com.example.cinema.api.services;

import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.enums.UserCategory;
import com.example.cinema.api.repositories.TicketRepository;
import com.example.cinema.api.ticketpricing.context.TicketPricingContext;
import com.example.cinema.api.ticketpricing.strategy.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }



    // TODO: Criar ticket

    // Recebe um ticket dto
    // Valida se o assento é valido e disponivel// o numero do seat do tikcet não pode passar da capacidade maxima do room associado a session
    // MovieSession precisa ser valida
    // salva ticket

    // assento não passa a capacidade maxima do room
    // e o assento não pode ser o mesmo de outro ticket


    public BigDecimal calculateTicketPrice(UserCategory userCategory, MovieSession session) {
        PricingStrategy strategy;

        if (session.getShowDate().getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            strategy = new WednesdayPromoPricing();
        } else if (userCategory == UserCategory.STUDENT) {
            strategy = new StudentPricing();
        } else if (userCategory == UserCategory.SENIOR) {
            strategy = new SeniorPricing();
        } else {
            strategy = new RegularPricing();
        }

        TicketPricingContext context = new TicketPricingContext(strategy);
        return context.executeStrategy(session);
    }




}
