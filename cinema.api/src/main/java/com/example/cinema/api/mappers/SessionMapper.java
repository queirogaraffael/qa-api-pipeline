package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mappings({
            @Mapping(target = "canceled", constant = "false"),
            @Mapping(target = "cinemaRoom", ignore = true),
            @Mapping(target = "movie", ignore = true),
            @Mapping(target = "purchases", ignore = true),
            @Mapping(target = "tickets", ignore = true)
    })
    MovieSession toEntity(MovieSessionRequestDTO movieSessionRequestDTO);


    @Mappings({
            @Mapping(expression = "java(movieSession.getStatus())", target = "status"),
            @Mapping(source = "movieSession.cinemaRoom.id", target = "roomId"),
            @Mapping(source = "movieSession.movie.id", target = "movieId")
    })
    MovieSessionResponseDTO toResponseDTO(MovieSession movieSession);


}
