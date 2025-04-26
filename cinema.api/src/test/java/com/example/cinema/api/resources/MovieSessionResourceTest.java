package com.example.cinema.api.resources;


import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import com.example.cinema.api.enums.MovieSessionStatus;
import com.example.cinema.api.repositories.MovieRepository;
import com.example.cinema.api.repositories.MovieSessionRepository;
import com.example.cinema.api.repositories.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieSessionResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieSessionRepository movieSessionRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @BeforeEach
    public void setUp() {
        movieSessionRepository.deleteAll();
        movieRepository.deleteAll();
        roomRepository.deleteAll();
    }

    @Test
    public void testCreateMovieSession() throws Exception {
        Room room = new Room();
        room.setNumber("Sala 1");
        room = roomRepository.save(room);

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movie = movieRepository.save(movie);

        MovieSessionRequestDTO dto = new MovieSessionRequestDTO();
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        dto.setStartTime(now);
        dto.setEndTime(now.plusHours(2));
        dto.setAvailableUntil(now.minusMinutes(30).plusHours(2));
        dto.setBasePrice(new BigDecimal("10.00"));
        dto.setStatus(MovieSessionStatus.SCHEDULED);
        dto.setRoomId(room.getId());
        dto.setMovieId(movie.getId());

        mockMvc.perform(post("/api/sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.roomId", is(room.getId().intValue())))
                .andExpect(jsonPath("$.movieId", is(movie.getId().intValue())))
                .andExpect(jsonPath("$.status", is("SCHEDULED")));
    }

    @Test
    public void testGetMovieSessionById() throws Exception {
        Room room = new Room();
        room.setNumber("Sala 2");
        room = roomRepository.save(room);

        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie = movieRepository.save(movie);

        MovieSession session = new MovieSession();
        LocalDateTime start = LocalDateTime.now().plusDays(1);
        session.setStartTime(start);
        session.setEndTime(start.plusHours(3));
        session.setAvailableUntil(start.plusHours(1));
        session.setBasePrice(new BigDecimal("15.00"));
        session.setStatus(MovieSessionStatus.SCHEDULED);
        session.setCinemaRoom(room);
        session.setMovie(movie);
        session = movieSessionRepository.save(session);

        mockMvc.perform(get("/api/sessions/{id}", session.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(session.getId().intValue())))
                .andExpect(jsonPath("$.movieId", is(movie.getId().intValue())))
                .andExpect(jsonPath("$.roomId", is(room.getId().intValue())));
    }

    @Test
    public void testGetAllMovieSessionsByRoomId() throws Exception {
        Room room = new Room();
        room.setNumber("Sala 3");
        room = roomRepository.save(room);

        Movie movie = new Movie();
        movie.setTitle("Avatar");
        movie = movieRepository.save(movie);

        for (int i = 0; i < 3; i++) {
            MovieSession session = new MovieSession();
            LocalDateTime start = LocalDateTime.now().plusDays(i + 1);
            session.setStartTime(start);
            session.setEndTime(start.plusHours(2));
            session.setAvailableUntil(start.plusHours(1));
            session.setBasePrice(new BigDecimal("12.00"));
            session.setStatus(MovieSessionStatus.SCHEDULED);
            session.setCinemaRoom(room);
            session.setMovie(movie);
            movieSessionRepository.save(session);
        }

        mockMvc.perform(get("/api/sessions/room/{roomId}?page=0&size=10", room.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].roomId", is(room.getId().intValue())));
    }

    @Test
    public void testGetAllMoviesSessionsDisponiveis() throws Exception {
        Room room = new Room();
        room.setNumber("Sala 4");
        room = roomRepository.save(room);

        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie = movieRepository.save(movie);

        MovieSession session = new MovieSession();
        LocalDateTime start = LocalDateTime.now().plusHours(5);
        session.setStartTime(start);
        session.setEndTime(start.plusHours(2));
        session.setAvailableUntil(LocalDateTime.now().plusHours(4));
        session.setBasePrice(new BigDecimal("20.00"));
        session.setStatus(MovieSessionStatus.SCHEDULED);
        session.setCinemaRoom(room);
        session.setMovie(movie);
        movieSessionRepository.save(session);

        mockMvc.perform(get("/api/sessions/available?page=0&size=10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$.content[0].status", is("SCHEDULED")));
    }
}

