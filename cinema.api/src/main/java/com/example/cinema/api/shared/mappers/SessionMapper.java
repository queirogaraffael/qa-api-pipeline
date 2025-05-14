package com.example.cinema.api.shared.mappers;

import com.example.cinema.api.shared.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.shared.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.domain.entities.MovieSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SessionMapper {


    MovieSession toEntity(MovieSessionRequestDTO movieSessionRequestDTO);



    MovieSessionResponseDTO toResponseDTO(MovieSession movieSession);


}
