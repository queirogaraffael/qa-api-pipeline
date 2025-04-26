package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.Movie;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SessionMapper {


    @Mappings({
            @Mapping(source = "movieSessionRequestDTO.status", target = "status"),
            @Mapping(source = "movieSessionRequestDTO.startTime", target = "startTime"),
            @Mapping(source = "movieSessionRequestDTO.endTime", target = "endTime"),
            @Mapping(source = "movieSessionRequestDTO.availableUntil", target = "availableUntil"),
            @Mapping(source = "movieSessionRequestDTO.basePrice", target = "basePrice")

    })
    MovieSession toEntity(MovieSessionRequestDTO movieSessionRequestDTO);


    @Mappings({
            @Mapping(source = "session.id", target = "id"),
            @Mapping(source = "session.startTime", target = "startTime"),
            @Mapping(source = "session.endTime", target = "endTime"),
            @Mapping(source = "session.availableUntil", target = "availableUntil"),
            @Mapping(source = "session.basePrice", target = "basePrice"),
            @Mapping(source = "session.status", target = "status")
    })
    MovieSessionResponseDTO toDTO(MovieSession session);


    @Mappings({
            @Mapping(source = "dto.status", target = "status"),
            @Mapping(source = "dto.startTime", target = "startTime"),
            @Mapping(source = "dto.endTime", target = "endTime"),
            @Mapping(source = "dto.availableUntil", target = "availableUntil"),
            @Mapping(source = "dto.basePrice", target = "basePrice"),
            @Mapping(source = "movie", target = "movie"),
            @Mapping(source = "room", target = "cinemaRoom"),
            @Mapping(target = "id", ignore = true)
    })
    MovieSession toEntity(MovieSessionRequestDTO dto, Room room, Movie movie);

    @Mappings({
            @Mapping(source = "movieSession.id", target = "id"),
            @Mapping(source = "movieSession.startTime", target = "startTime"),
            @Mapping(source = "movieSession.endTime", target = "endTime"),
            @Mapping(source = "movieSession.availableUntil", target = "availableUntil"),
            @Mapping(source = "movieSession.basePrice", target = "basePrice"),
            @Mapping(source = "movieSession.status", target = "status"),
            @Mapping(source = "room.id", target = "roomId"),
            @Mapping(source = "movie.id", target = "movieId")
    })
    MovieSessionResponseDTO toResponseDTO(MovieSession movieSession, Room room, Movie movie);

}
