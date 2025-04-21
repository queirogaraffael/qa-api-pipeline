package com.example.cinema.api.dtos.session;

import com.example.cinema.api.enums.SessionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionSimpleDTO {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime availableUntil;

    private double ticketPrice;

    private SessionStatus status;

}
