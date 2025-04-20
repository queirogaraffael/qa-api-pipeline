package com.example.cinema.api.services;

import com.example.cinema.api.dtos.room.RoomRequestDTO;
import com.example.cinema.api.dtos.room.RoomResponseDTO;
import com.example.cinema.api.entities.Room;
import com.example.cinema.api.exceptions.NumeroDeQuartoJaCadastradoException;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.RoomMapper;
import com.example.cinema.api.repositories.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }


    public RoomResponseDTO createRoom(RoomRequestDTO roomRequestDTO) {

        if(roomRepository.existsByNumber(roomRequestDTO.getNumber())) {
            throw new NumeroDeQuartoJaCadastradoException("Número de sala já cadastrado");
        }

        Room room = roomMapper.toEntity(roomRequestDTO);
        room = roomRepository.save(room);
        return roomMapper.toDTO(room);
    }


    public RoomResponseDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada"));
        return roomMapper.toDTO(room);
    }


    public Page<RoomResponseDTO> getAllRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return roomRepository.findAllPaginado(pageable);
    }


    public RoomResponseDTO updateRoom(Long id, RoomRequestDTO roomRequestDTO) {

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada para modificação"));

        if (roomRepository.existsByNumber(roomRequestDTO.getNumber()) && !room.getNumber().equals(roomRequestDTO.getNumber())) {
            throw new NumeroDeQuartoJaCadastradoException("Número de sala já cadastrado");
        }

        roomMapper.updateEntityFromDTO(roomRequestDTO, room);
        return roomMapper.toDTO(roomRepository.save(room));
    }


}
