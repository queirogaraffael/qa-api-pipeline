package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.SessionMapper;
import com.example.cinema.api.repositories.MovieRepository;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.RoomRepository;
import org.springframework.stereotype.Service;

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

        Movie movie = movieRepository.findById(dto.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));

        Room room = roomRepository.findById(dto.getRoomId()).orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));

        movieSessionValidator.validateSessionConflicts(dto);

        MovieSession movieSession = sessionMapper.toEntity(dto);

        // associar movie e room a movieSession
        movieSession.setMovie(movie);
        movieSession.setCinemaRoom(room);

        movieSession = movieSessionRepository.save(movieSession);

        return sessionMapper.toResponseDTO(movieSession);

    }

}



