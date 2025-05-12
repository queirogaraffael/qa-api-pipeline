package com.example.cinema.api.dtos.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {

    private Long id;
    private int seatNumber;

    private Long userId;
    private Long movieSessionId;
    private Long purchaseId;
}
