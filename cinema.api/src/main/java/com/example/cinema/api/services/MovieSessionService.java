package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionUpdateDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import com.example.cinema.api.enums.MovieSessionStatus;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.SessionMapper;
import com.example.cinema.api.repositories.MovieRepository;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        MovieSession movieSession = sessionMapper.toEntity(dto, room, movie);

        movieSession = movieSessionRepository.save(movieSession);

        return sessionMapper.toResponseDTO(movieSession, room, movie);

    }


    public MovieSessionResponseDTO getSessionById(Long id) {
        return movieSessionRepository.findMovieSessionDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));
    }


    // TODO: modificar para pegar todos disponiveis
    public Page<MovieSessionResponseDTO> getAllPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return movieSessionRepository.findAllPaginado(pageable);
    }


    public Page<MovieSessionResponseDTO> getSessionsByRoomId(Long roomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return movieSessionRepository.findAllByRoomId(roomId, pageable);
    }


    // TODO: cancelar uma sessão pode ter efeitos colaterais
    // Se tiver compras associadas, não pode cancelar
    public String cancelSession(Long id) {
        MovieSession movieSession = movieSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));

        if (movieSession.getStatus() == MovieSessionStatus.CANCELED) {
            throw new IllegalArgumentException("Sessão já está cancelada");
        }

        movieSession.setStatus(MovieSessionStatus.CANCELED);
        movieSessionRepository.save(movieSession);

        return "Sessão cancelada com sucesso";
    }


    // TODO: get all paginados disponiveis
    // Levar em consideração apenas os status ativo e schedule


    // TODO: Editar MovieSession
    // Não pode ter sala reservada para o mesmo horário
    // STATUS: CANCELED ou FINISHED não pode ser editado
   /* public MovieResponseDTO updateSession(Long id, MovieSessionUpdateDTO dto) {
        validateSessionUpdate(dto);

        MovieSession movieSession = movieSessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));

        //
        validateScheduleConflicts(dto, room);



        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));

        sessionMapper.updateEntity(movieSession, dto, room, movie);

        return sessionMapper.toResponseDTO(movieSession, room, movie);
    }*/

    // TODO: metodo que modifica o status
    //


    private void validateSessionRequest(MovieSessionRequestDTO dto) {
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

        if (dto.getAvailableUntil() != null &&
                dto.getAvailableUntil().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("O campo availableUntil não pode ser no passado.");
        }

    }

    private void validateSessionUpdate(MovieSessionUpdateDTO dto) {

        if (dto.getStartTime().isAfter(dto.getEndTime())) {
            throw new IllegalArgumentException("A data de início não pode ser depois da data de término");
        }

        if (dto.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do ingresso deve ser maior que zero");
        }

    }

    private void validateScheduleConflicts(MovieSessionRequestDTO dto, Room room) {
        boolean conflict = movieSessionRepository.existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(
                room, dto.getEndTime(), dto.getStartTime());

        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }

    private void validateScheduleConflicts(MovieSessionUpdateDTO dto, Room room) {
        boolean conflict = movieSessionRepository.existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(
                room, dto.getEndTime(), dto.getStartTime());

        if (conflict) {
            throw new IllegalArgumentException("A sala já está reservada para esse horário");
        }
    }
}



