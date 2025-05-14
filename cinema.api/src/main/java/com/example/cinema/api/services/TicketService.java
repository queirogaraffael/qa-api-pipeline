package com.example.cinema.api.services;

import com.example.cinema.api.dtos.tickets.TicketRequestDTO;
import com.example.cinema.api.dtos.tickets.TicketResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Ticket;
import com.example.cinema.api.entities.User;
import com.example.cinema.api.enums.UserCategory;
import com.example.cinema.api.mappers.TicketMapper;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.TicketRepository;
import com.example.cinema.api.ticketpricing.context.TicketPricingContext;
import com.example.cinema.api.ticketpricing.strategy.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MovieSessionRepository movieSessionRepository;
    private final TicketMapper ticketMapper;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, MovieSessionRepository movieSessionRepository, TicketMapper ticketMapper, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.ticketMapper = ticketMapper;
        this.userService = userService;
    }

    public TicketResponseDTO criarTickt(TicketRequestDTO ticketRequestDTO) {

        Integer roomCapacity = movieSessionRepository.findRoomCapacityByMovieSessionId(ticketRequestDTO.getMovieSessionId());

        if (ticketRequestDTO.getSeatNumber() > roomCapacity) {
            throw new IllegalArgumentException("Assento inválido");
        }

        boolean isSeatTaken = ticketRepository.isSeatTaken(ticketRequestDTO.getSeatNumber(), ticketRequestDTO.getMovieSessionId());

        if (isSeatTaken) {
            throw new IllegalArgumentException("Assento já reservado");
        }

        MovieSession movieSession = movieSessionRepository.findById(ticketRequestDTO.getMovieSessionId())
                .orElseThrow(() -> new IllegalArgumentException("Sessão de filme não encontrada"));

        Ticket ticket = ticketMapper.toEntity(ticketRequestDTO);

        ticket.setMovieSession(movieSession);

        User user = userService.getAuthenticatedUser();

        ticket.setUser(user);

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toResponseDTO(savedTicket);
    }


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
