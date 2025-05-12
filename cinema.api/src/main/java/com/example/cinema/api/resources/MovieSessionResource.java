package com.example.cinema.api.resources;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.services.MovieSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
public class MovieSessionResource {

    private final MovieSessionService movieSessionService;

    public MovieSessionResource(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @Operation(summary = "Criar nova sessão de filme", description = "Cria uma nova sessão de filme")
    @ApiResponse(responseCode = "201", description = "Sessão criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de validação")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor | Erro em alguma validação de negócio")
    @PostMapping
    public ResponseEntity<MovieSessionResponseDTO> createMovieSession(@RequestBody @Valid MovieSessionRequestDTO dto) {
        MovieSessionResponseDTO movieSessionResponseDTO = movieSessionService.createSession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSessionResponseDTO);
    }

}
