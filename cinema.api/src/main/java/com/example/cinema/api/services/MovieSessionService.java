package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import com.example.cinema.api.enums.MovieSessionStatus;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.SessionMapper;
import com.example.cinema.api.repositories.MovieRepository;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final SessionMapper sessionMapper;

    public MovieSessionService(MovieSessionRepository movieSessionRepository,
                               MovieRepository movieRepository,
                               RoomRepository roomRepository,
                               SessionMapper sessionMapper) {
        this.movieSessionRepository = movieSessionRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.sessionMapper = sessionMapper;
    }

    public MovieSessionResponseDTO createSession(MovieSessionRequestDTO dto) {
        validateSessionRequest(dto);

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));

        validateScheduleConflicts(dto, room);

        MovieSession movieSession = new MovieSession();
        movieSession.setStatus(dto.getStatus());
        movieSession.setStartTime(dto.getStartTime());
        movieSession.setEndTime(dto.getEndTime());
        movieSession.setAvailableUntil(dto.getAvailableUntil());
        movieSession.setTicketPrice(dto.getTicketPrice());
        movieSession.setMovie(movie);
        movieSession.setCinemaRoom(room);

        movieSession = movieSessionRepository.save(movieSession);

        MovieSessionResponseDTO responseDTO = new MovieSessionResponseDTO();
        responseDTO.setId(movieSession.getId());
        responseDTO.setStartTime(movieSession.getStartTime());
        responseDTO.setEndTime(movieSession.getEndTime());
        responseDTO.setAvailableUntil(movieSession.getAvailableUntil());
        responseDTO.setTicketPrice(movieSession.getTicketPrice());
        responseDTO.setStatus(movieSession.getStatus());
        responseDTO.setRoomId(room.getId());
        responseDTO.setMovieId(movie.getId());

        return responseDTO;
    }

    private void validateSessionRequest(MovieSessionRequestDTO dto) {
        if (dto.getStartTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data de início não pode ser menor que a data atual");
        }

        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new IllegalArgumentException("A data de início não pode ser depois da data de término");
        }

        if (dto.getTicketPrice() <= 0) {
            throw new IllegalArgumentException("O preço do ingresso deve ser maior que zero");
        }

        if (dto.getStatus() == MovieSessionStatus.CANCELED || dto.getStatus() == MovieSessionStatus.FINISHED) {
            throw new IllegalArgumentException("O status da sessão não pode ser CANCELLED ou FINISHED");
        }

        if (dto.getAvailableUntil() != null &&
                dto.getAvailableUntil().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("O campo availableUntil não pode ser no passado.");
        }

    }

    private void validateScheduleConflicts(MovieSessionRequestDTO dto, Room room) {
        boolean conflict = movieSessionRepository.existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(
                room, dto.getEndTime(), dto.getStartTime());

        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }
}


// Criar ->
// Pegar por id
// Pegar todas paginadas
// Editar

// Quartos associado a uma seção
// tikcets/usuarios associados a uma seção
// quais seções estão associadas aquele quarto ?
// quais tikets/users estão associados aquela seção ?



