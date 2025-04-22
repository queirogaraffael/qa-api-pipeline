package com.example.cinema.api.mappers;

import com.example.cinema.api.dtos.room.RoomRequestDTO;
import com.example.cinema.api.dtos.room.RoomResponseDTO;
import com.example.cinema.api.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    Room toEntity(RoomRequestDTO roomRequestDTO);

    RoomResponseDTO toDTO(Room room);

    void updateEntityFromDTO(RoomRequestDTO dto, @MappingTarget Room room);

}
