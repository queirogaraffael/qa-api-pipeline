package com.example.cinema.api.shared.mappers;

import com.example.cinema.api.domain.entities.MovieSession;
import com.example.cinema.api.shared.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.shared.dtos.movieSession.MovieSessionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    MovieSession toEntity(MovieSessionRequestDTO movieSessionRequestDTO);

    MovieSessionResponseDTO toResponseDTO(MovieSession movieSession);


}
