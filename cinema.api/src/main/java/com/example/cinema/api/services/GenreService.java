package com.example.cinema.api.services;

import com.example.cinema.api.dtos.genre.GenreRequestDTO;
import com.example.cinema.api.dtos.genre.GenreResponseDTO;
import com.example.cinema.api.dtos.genre.GenreUpdateDTO;
import com.example.cinema.api.entities.Genre;
import com.example.cinema.api.exceptions.GeneroJaExisteException;
import com.example.cinema.api.exceptions.ResourceNotFoundException;
import com.example.cinema.api.mappers.GenreMapper;
import com.example.cinema.api.repositories.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    public GenreResponseDTO create(GenreRequestDTO dto) {
        Genre genero = genreMapper.toEntity(dto);
        return genreMapper.toDTO(genreRepository.save(genero));
    }

    public GenreResponseDTO findById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gênero não encontrado"));
        return genreMapper.toDTO(genre);
    }

    public Page<GenreResponseDTO> findAllPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return genreRepository.findAllBy(pageable).map(genreMapper::toDTO);
    }

    public Page<GenreResponseDTO> findByNameContainingIgnoreCase(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return genreRepository.findByNameContainingIgnoreCase(name, pageable).map(genreMapper::toDTO);
    }

    public GenreResponseDTO update(Long id, GenreUpdateDTO dto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gênero não encontrado"));

        if(genreRepository.existsByName(dto.getName()) && !Objects.equals(dto.getName(), genre.getName())){
            throw new GeneroJaExisteException("Gênero com o nome " + dto.getName() + " já existe");
        }

        genreMapper.updateEntityFromDTO(dto, genre);
        return genreMapper.toDTO(genreRepository.save(genre));

    }

}

