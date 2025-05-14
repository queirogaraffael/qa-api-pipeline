package com.example.cinema.api.services;

import com.example.cinema.api.dtos.purchase.PurchaseRequestDTO;
import com.example.cinema.api.dtos.purchase.PurchaseResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Purchase;
import com.example.cinema.api.entities.Ticket;
import com.example.cinema.api.entities.User;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.PurchaseMapper;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.PurchaseRepository;
import com.example.cinema.api.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final TicketService ticketService;
    private final TicketRepository ticketRepository;
    private final MovieSessionRepository movieSessionRepository;
    private final PurchaseMapper purchaseMapper;
    private final UserService userService;

    public PurchaseService(PurchaseRepository purchaseRepository, TicketService ticketService, TicketRepository ticketRepository, MovieSessionRepository movieSessionRepository, PurchaseMapper purchaseMapper, UserService userService) {
        this.purchaseRepository = purchaseRepository;
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.purchaseMapper = purchaseMapper;
        this.userService = userService;
    }

    public PurchaseResponseDTO createPurchase(PurchaseRequestDTO purchaseRequestDTO) {

        Ticket ticket = ticketRepository.findById(purchaseRequestDTO.getTicketId()).orElseThrow(() -> new ResourceNotFoundException("Ticket nao encontrado"));
        MovieSession movieSession = movieSessionRepository.findById(purchaseRequestDTO.getMovieSessionId()).orElseThrow(() -> new ResourceNotFoundException("MovieSession nao encontrado"));

        Purchase purchase = purchaseMapper.toEntity(purchaseRequestDTO);
        purchase.setTicket(ticket);
        purchase.setMovieSession(movieSession);
        purchase.setPurchaseDate(LocalDateTime.now());

        User user = userService.getAuthenticatedUser();

        purchase.setUser(user);

        BigDecimal ticketPrice = ticketService.calculateTicketPrice(user.getCategory(), movieSession);

        purchase.setTotalPrice(ticketPrice);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        return purchaseMapper.toResponseDTO(savedPurchase);

        // avisar usuario

    }
}
