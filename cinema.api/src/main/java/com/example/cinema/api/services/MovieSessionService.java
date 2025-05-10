package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.SessionMapper;
import com.example.cinema.api.repositories.MovieRepository;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieSessionService {

    private final MovieSessionRepository movieSessionRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final SessionMapper sessionMapper;
    private final MovieSessionValidator movieSessionValidator;

    public MovieSessionService(MovieSessionRepository movieSessionRepository,
                               MovieRepository movieRepository,
                               RoomRepository roomRepository,
                               SessionMapper sessionMapper, MovieSessionValidator movieSessionValidator) {
        this.movieSessionRepository = movieSessionRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.sessionMapper = sessionMapper;
        this.movieSessionValidator = movieSessionValidator;
    }

    public MovieSessionResponseDTO createSession(MovieSessionRequestDTO dto) {
        movieSessionValidator.validateSessionRequest(dto);

        if(!movieRepository.existsById(dto.getMovieId())){
            throw new ResourceNotFoundException("Filme não encontrado");
        }

       if(!roomRepository.existsById(dto.getRoomId())){
           throw new ResourceNotFoundException("Sala não encontrada");

       }

        movieSessionValidator.validateSessionConflicts(dto);

        MovieSession movieSession = sessionMapper.toEntity(dto);

        movieSession = movieSessionRepository.save(movieSession);

        return sessionMapper.toResponseDTO(movieSession, dto.getRoomId(), dto.getMovieId());

    }


    public MovieSessionResponseDTO getSessionById(Long id) {
        return movieSessionRepository.findMovieSessionDtoById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));
    }


    public Page<MovieSessionResponseDTO> getSessionsByRoomId(Long roomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return movieSessionRepository.findAllByRoomId(roomId, pageable);
    }


    public Page<MovieSessionResponseDTO> getAllMovieSessionsDisponiveisPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return movieSessionRepository.findAllDisponiveisPaginado(LocalDateTime.now(), pageable);
    }


}



