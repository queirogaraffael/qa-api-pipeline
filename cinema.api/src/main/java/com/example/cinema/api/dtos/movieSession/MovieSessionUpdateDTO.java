package com.example.cinema.api.dtos.movieSession;

import com.example.cinema.api.enums.MovieSessionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSessionUpdateDTO {

    private LocalDate availableFrom;
    private LocalDate availableUntil;
    private LocalTime startTime;
    private LocalTime endTime;

    private BigDecimal basePrice;

    private Long roomId;
    private Long movieId;

}
