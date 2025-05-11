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
            @Mapping(source = "movieSessionRequestDTO.showDate", target = "showDate"),
            @Mapping(source = "movieSessionRequestDTO.startTime", target = "startTime"),
            @Mapping(source = "movieSessionRequestDTO.endTime", target = "endTime"),
            @Mapping(source = "movieSessionRequestDTO.basePrice", target = "basePrice"),
            @Mapping(target = "canceled", constant = "false"),
            @Mapping(target = "cinemaRoom", ignore = true),
            @Mapping(target = "movie", ignore = true),
            @Mapping(target = "purchases", ignore = true),
            @Mapping(target = "tickets", ignore = true)
    })
    MovieSession toEntity(MovieSessionRequestDTO movieSessionRequestDTO);


    @Mappings({
            @Mapping(source = "movieSession.id", target = "id"),
            @Mapping(source = "movieSession.showDate", target = "showDate"),
            @Mapping(source = "movieSession.startTime", target = "startTime"),
            @Mapping(source = "movieSession.endTime", target = "endTime"),
            @Mapping(source = "movieSession.basePrice", target = "basePrice"),
            @Mapping(expression = "java(movieSession.getStatus())", target = "status"),
            @Mapping(source = "movieSession.cinemaRoom.id", target = "roomId"),
            @Mapping(source = "movieSession.movie.id", target = "movieId")
    })
    MovieSessionResponseDTO toResponseDTO(MovieSession movieSession);


}
