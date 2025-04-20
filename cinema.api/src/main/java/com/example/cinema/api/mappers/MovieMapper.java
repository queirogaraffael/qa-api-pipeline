package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.movie.MovieRequestDTO;
import com.example.cinema.api.dtos.movie.MovieResponseDTO;
import com.example.cinema.api.entities.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toEntity(MovieRequestDTO dto);

    MovieResponseDTO toDTO(Movie movie);

}
