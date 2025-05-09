package com.example.cinema.api.dtos.tickets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {

    private int seatNumber;
    private LocalDate usageDate;

    private Long userId;
    private Long movieSessionId;
}
