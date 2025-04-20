package com.example.cinema.api.resources;

import com.example.cinema.api.dtos.movie.MovieRequestDTO;
import com.example.cinema.api.dtos.movie.MovieResponseDTO;
import com.example.cinema.api.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieResource {

    private final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Criar novo filme", description = "Cria um novo filme")
    @ApiResponse(responseCode = "201", description = "Filme criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de validação")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/{genreId}")
    public ResponseEntity<MovieResponseDTO> createMovie(@RequestBody @Valid MovieRequestDTO dto, @PathVariable Long genreId) {
        MovieResponseDTO movieResponseDTO = movieService.createMovie(dto, genreId);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponseDTO);
    }



}
