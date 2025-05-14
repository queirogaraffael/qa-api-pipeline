package com.example.cinema.api.resources;


import com.example.cinema.api.infrastructure.repositories.MovieRepository;
import com.example.cinema.api.infrastructure.repositories.MovieSessionRepository;
import com.example.cinema.api.infrastructure.repositories.RoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

