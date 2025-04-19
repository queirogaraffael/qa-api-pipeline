package com.example.cinema.api.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String cinemaRoom;

    private int availableSeats;
    private double ticketPrice;

    private Movie movie;

}
