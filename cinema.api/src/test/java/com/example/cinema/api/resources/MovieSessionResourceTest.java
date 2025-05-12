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

}

