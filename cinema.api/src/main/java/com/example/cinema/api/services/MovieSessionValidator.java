package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionUpdateDTO;
import com.example.cinema.api.entities.Room;
import com.example.cinema.api.enums.MovieSessionStatus;
import com.example.cinema.api.repositories.MovieSessionRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class MovieSessionValidator {

    private final MovieSessionRepository movieSessionRepository;

    public MovieSessionValidator(MovieSessionRepository movieSessionRepository) {
        this.movieSessionRepository = movieSessionRepository;
    }

    public void validateSessionRequest(MovieSessionRequestDTO dto) {
        if (dto.getStartTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de início não pode ser menor que a data atual");
        }
        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new IllegalArgumentException("A data de início não pode ser depois da data de término");
        }
        if (dto.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do ingresso deve ser maior que zero");
        }
        if (dto.getStatus() == MovieSessionStatus.CANCELED || dto.getStatus() == MovieSessionStatus.FINISHED) {
            throw new IllegalArgumentException("O status da sessão não pode ser CANCELLED ou FINISHED");
        }
        if (dto.getAvailableUntil() != null && dto.getAvailableUntil().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("O campo availableUntil não pode ser no passado.");
        }
    }

    public void validateSessionUpdate(MovieSessionUpdateDTO dto) {
        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new IllegalArgumentException("A data de início não pode ser depois da data de término");
        }
        if (dto.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do ingresso deve ser maior que zero");
        }
    }

    public void validateScheduleConflicts(MovieSessionRequestDTO dto, Room room) {
        boolean conflict = movieSessionRepository.existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(room, dto.getEndTime(), dto.getStartTime());
        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }

    public void validateScheduleConflicts(MovieSessionUpdateDTO dto, Room room) {
        boolean conflict = movieSessionRepository.existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(room, dto.getEndTime(), dto.getStartTime());
        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }


}
