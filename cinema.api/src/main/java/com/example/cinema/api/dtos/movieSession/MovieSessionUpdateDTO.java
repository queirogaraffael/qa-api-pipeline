package com.example.cinema.api.dtos.movieSession;

import com.example.cinema.api.enums.MovieSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSessionUpdateDTO {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime availableUntil;

    private BigDecimal basePrice;

    private MovieSessionStatus status;

    private Long roomId;
    private Long movieId;

}
