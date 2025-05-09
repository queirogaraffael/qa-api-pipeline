package com.example.cinema.api.services;

import com.example.cinema.api.dtos.user.UserCategoryDTO;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.User;
import com.example.cinema.api.repositories.TicketRepository;
import com.example.cinema.api.ticketpricing.context.TicketPricingContext;
import com.example.cinema.api.ticketpricing.strategy.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    // TODO: Criar ticket

    // Recebe um ticket dto
    // Valida se o assento é valido e disponivel
    // MovieSession precisa ser valida
    // salva ticket


    // O ticket precisa ter um campo de data da sessão
    // essa data não pode ser anterior a o dia de hoje e nao pode ser posterior ao dia de disponibilidade
    // maxima da sessão


    // criar um metodo em que verifique a questão do assento não passa a capacidade maxima do room
    // e o assento não pode ser o mesmo de outro ticket


    public BigDecimal calculateTicketPrice(UserCategoryDTO userCategoryDTO, MovieSession session) {
        PricingStrategy strategy;

        if (session.isOnWednesday()) {
            strategy = new WednesdayPromoPricing();
        } else if (userCategoryDTO.isStudent()) {
            strategy = new StudentPricing();
        } else if (userCategoryDTO.isSenior()) {
            strategy = new SeniorPricing();
        } else {
            strategy = new RegularPricing();
        }

        TicketPricingContext context = new TicketPricingContext(strategy);
        return context.executeStrategy(session);
    }




}
