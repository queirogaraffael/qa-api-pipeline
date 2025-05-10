package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionUpdateDTO;
import com.example.cinema.api.enums.MovieSessionStatus;
import com.example.cinema.api.repositories.MovieSessionRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class MovieSessionValidator {

    private final MovieSessionRepository movieSessionRepository;
    private final Clock clock;

    public MovieSessionValidator(MovieSessionRepository movieSessionRepository, Clock clock) {
        this.movieSessionRepository = movieSessionRepository;
        this.clock = clock;
    }

    public void validateSessionRequest(MovieSessionRequestDTO dto) {
        validateDateRange(dto.getAvailableFrom(), dto.getAvailableUntil());
        validateTimeRange(dto.getStartTime(), dto.getEndTime());
        validateBasePrice(dto.getBasePrice());
        validateStatus(dto.getStatus());
        validateAvailableUntilNotInPast(dto.getAvailableUntil());
    }

    public void validateSessionUpdate(MovieSessionUpdateDTO dto) {
        validateDateRange(dto.getAvailableFrom(), dto.getAvailableUntil());
        validateTimeRange(dto.getStartTime(), dto.getEndTime());
        validateBasePrice(dto.getBasePrice());
        validateAvailableUntilNotInPast(dto.getAvailableUntil());
    }

    public void validateSessionConflicts(MovieSessionUpdateDTO dto) {
        boolean conflict = movieSessionRepository.existsSessionConflict(
                dto.getRoomId(),
                dto.getAvailableFrom(),
                dto.getAvailableUntil(),
                dto.getStartTime(),
                dto.getEndTime()
        );
        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }

    public void validateSessionConflicts(MovieSessionRequestDTO dto) {
        boolean conflict = movieSessionRepository.existsSessionConflict(
                dto.getRoomId(),
                dto.getAvailableFrom(),
                dto.getAvailableUntil(),
                dto.getStartTime(),
                dto.getEndTime()
        );
        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }

    private void validateDateRange(LocalDate from, LocalDate until) {
        if (from == null || until == null) {
            throw new IllegalArgumentException("As datas de disponibilidade não podem ser nulas");
        }
        if (from.isAfter(until)) {
            throw new IllegalArgumentException("A data de início de disponibilidade não pode ser depois da data de término.");
        }
    }

    private void validateTimeRange(LocalTime start, LocalTime end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Os horários de início e término não podem ser nulos");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("A hora de início não pode ser depois da hora de término.");
        }
    }

    private void validateBasePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do ingresso deve ser maior que zero.");
        }
    }

    private void validateStatus(MovieSessionStatus status) {
        if (status == MovieSessionStatus.CANCELED || status == MovieSessionStatus.FINISHED) {
            throw new IllegalArgumentException("O status da sessão não pode ser CANCELLED ou FINISHED na criação.");
        }
    }

    private void validateAvailableUntilNotInPast(LocalDate availableUntil) {
        if (availableUntil != null && availableUntil.isBefore(LocalDate.now(clock))) {
            throw new IllegalArgumentException("O campo availableUntil não pode estar no passado.");
        }
    }
}

