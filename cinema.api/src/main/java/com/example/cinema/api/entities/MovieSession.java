package com.example.cinema.api.entities;

import com.example.cinema.api.enums.MovieSessionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal basePrice;
    private LocalDateTime availableUntil;

    @Enumerated(EnumType.STRING)
    private MovieSessionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room cinemaRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "movieSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "movieSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

}
