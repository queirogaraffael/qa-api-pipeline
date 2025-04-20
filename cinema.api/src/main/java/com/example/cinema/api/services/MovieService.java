package com.example.cinema.api.services;

import com.example.cinema.api.dtos.movie.MovieRequestDTO;
import com.example.cinema.api.dtos.movie.MovieResponseDTO;
import com.example.cinema.api.entities.Genre;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.MovieMapper;
import com.example.cinema.api.repositories.GenreRepository;
import com.example.cinema.api.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }

    public MovieResponseDTO createMovie(MovieRequestDTO dto, Long genreId) {
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("Gênero não encontrado"));

        Movie movie = movieMapper.toEntity(dto);
        movie.setGenre(genre);
        Movie movieSaved = movieRepository.save(movie);

        return movieMapper.toDTO(movieSaved);
    }

    // Listar filmes paginados
    // Buscar filmes paginados por genero
    // Buscar filmes paginados por nome contando
    // Buscar filmes paginados por nome e genero
    // Buscar filme por id

    // Atualizar filme
}
