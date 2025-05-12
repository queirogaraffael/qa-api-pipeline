package com.example.cinema.api.services;

import com.example.cinema.api.repositories.TicketRepository;
import org.springframework.stereotype.Service;

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


    /*
    public BigDecimal calculateTicketPrice(UserCategoryDTO userCategoryDTO) {
        PricingStrategy strategy;

        if (session. isOnWednesday()) {
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
*/



}
