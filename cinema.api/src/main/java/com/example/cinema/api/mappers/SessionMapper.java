package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    MovieSession toEntity(MovieSessionRequestDTO movieSessionRequestDTO);

    MovieSessionResponseDTO toDTO(MovieSession session);

}
