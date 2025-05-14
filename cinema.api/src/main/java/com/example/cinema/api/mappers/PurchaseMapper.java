package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.purchase.PurchaseRequestDTO;
import com.example.cinema.api.dtos.purchase.PurchaseResponseDTO;
import com.example.cinema.api.entities.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    @Mapping(target = "ticket", ignore = true)
    @Mapping(target = "movieSession", ignore = true)
    Purchase toEntity(PurchaseRequestDTO purchaseRequestDTO);

    @Mapping(source = "purchase.user.id", target = "userId")
    @Mapping(source = "purchase.ticket.id", target = "ticketId")
    @Mapping(source = "purchase.movieSession.id", target = "movieSessionId")
    PurchaseResponseDTO toResponseDTO(Purchase purchase);
}
