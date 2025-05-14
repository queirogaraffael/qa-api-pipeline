package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.movie.MovieRequestDTO;
import com.example.cinema.api.dtos.movie.MovieResponseDTO;
import com.example.cinema.api.dtos.movie.MovieUpdateDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.repositories.projection.MovieResponseDTOProjection;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "genre", ignore = true)
    @Mapping(target = "movieSessions", ignore = true)
    Movie toEntity(MovieRequestDTO dto);

    @BeanMapping(ignoreByDefault = true)
    MovieResponseDTO toDTO(Movie movie);

    MovieResponseDTO projectionToDTO(MovieResponseDTOProjection movie);

    @BeanMapping(ignoreByDefault = true)
    void updateEntityFromDTO(MovieUpdateDTO dto, @MappingTarget Movie movie);

}

