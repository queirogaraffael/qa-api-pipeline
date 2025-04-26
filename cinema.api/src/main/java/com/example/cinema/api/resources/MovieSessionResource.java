package com.example.cinema.api.resources;

import com.example.cinema.api.dtos.movieSession.MovieSessionRequestDTO;
import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.services.MovieSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "Buscar sessão de filme por ID", description = "Busca uma sessão de filme pelo ID")
    @ApiResponse(responseCode = "200", description = "Sessão encontrada")
    @ApiResponse(responseCode = "404", description = "Sessão não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/{id}")
    public ResponseEntity<MovieSessionResponseDTO> getMovieSessionById(@PathVariable Long id) {
        MovieSessionResponseDTO movieSession = movieSessionService.getSessionById(id);
        return ResponseEntity.ok(movieSession);
    }


    @Operation(summary = "Busca paginada de todas as sessões de filme por ID da sala", description = "Busca todas as sessões de filme por ID da sala")
    @ApiResponse(responseCode = "200", description = "Sessões encontradas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/room/{roomId}")
    public ResponseEntity<Page<MovieSessionResponseDTO>> getAllMovieSessionsByRoomId(
            @PathVariable Long roomId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MovieSessionResponseDTO> movieSessions = movieSessionService.getSessionsByRoomId(roomId, page, size);
        return ResponseEntity.ok(movieSessions);

    }


    @Operation(summary = "Busca paginada de todas as sessões de filme disponiveis", description = "Busca todas as sessões de filme")
    @ApiResponse(responseCode = "200", description = "Sessões encontradas")
    @ApiResponse(responseCode = "404", description = "Sessões não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/available")
    public ResponseEntity<Page<MovieSessionResponseDTO>> getAllMoviesSessionsDisponiveis(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MovieSessionResponseDTO> movieSessions = movieSessionService.getAllMovieSessionsDisponiveisPaginados(page, size);
        return ResponseEntity.ok(movieSessions);
    }

}
