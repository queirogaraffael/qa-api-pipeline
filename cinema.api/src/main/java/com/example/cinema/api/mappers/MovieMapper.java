package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.movie.MovieRequestDTO;
import com.example.cinema.api.dtos.movie.MovieResponseDTO;
import com.example.cinema.api.dtos.movie.MovieUpdateDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.repositories.projection.MovieResponseDTOProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie toEntity(MovieRequestDTO dto);

    MovieResponseDTO toDTO(Movie movie);
    MovieResponseDTO toDTO(MovieResponseDTOProjection movie);

    void updateEntityFromDTO(MovieUpdateDTO dto, @MappingTarget Movie movie);

    MovieResponseDTO toDTO(MovieResponseDTO movieResponseDTO);
}
