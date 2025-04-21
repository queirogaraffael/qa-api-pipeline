package com.example.cinema.api.dtos.session;

import com.example.cinema.api.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponseDTO {

    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime availableUntil;

    private double ticketPrice;

    private SessionStatus status;

    private Long roomId;
    private Long movieId;
}
