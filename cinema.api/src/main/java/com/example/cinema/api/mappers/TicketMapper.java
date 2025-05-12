package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.tickets.TicketRequestDTO;
import com.example.cinema.api.dtos.tickets.TicketResponseDTO;
import com.example.cinema.api.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    Ticket toEntity(TicketRequestDTO ticketRequestDTO);

    @Mapping(source = "ticket.user.id", target = "userId")
    @Mapping(source = "ticket.movieSession.id", target = "movieSessionId")
    @Mapping(source = "ticket.purchase.id", target = "purchaseId")
    TicketResponseDTO toResponseDTO(Ticket ticket);
}
