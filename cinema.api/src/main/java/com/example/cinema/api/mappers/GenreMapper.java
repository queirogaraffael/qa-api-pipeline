package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.genre.GenreRequestDTO;
import com.example.cinema.api.dtos.genre.GenreResponseDTO;
import com.example.cinema.api.dtos.genre.GenreUpdateDTO;
import com.example.cinema.api.entities.Genre;
import com.example.cinema.api.repositories.projection.GenreResponseDTOProjection;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    Genre toEntity(GenreRequestDTO dto);
    GenreResponseDTO toDTO(Genre genre);
    void updateEntityFromDTO(GenreUpdateDTO dto, @MappingTarget Genre genre);
    GenreResponseDTO toDTO(GenreResponseDTOProjection projection);

}
