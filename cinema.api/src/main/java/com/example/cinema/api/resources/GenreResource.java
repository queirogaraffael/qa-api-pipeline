package com.example.cinema.api.resources;

import com.example.cinema.api.dtos.genre.GenreRequestDTO;
import com.example.cinema.api.dtos.genre.GenreResponseDTO;
import com.example.cinema.api.dtos.genre.GenreUpdateDTO;
import com.example.cinema.api.services.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gêneros")
@RestController
@RequestMapping("/api/genres")
public class GenreResource {

    private final GenreService genreService;

    public GenreResource(GenreService genreService) {
        this.genreService = genreService;
    }

    @Operation(summary = "Criar novo gênero", description = "Cria um novo gênero")
    @ApiResponse(responseCode = "201", description = "Gênero criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de validação")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping()
    public ResponseEntity<GenreResponseDTO> createGenre(@RequestBody @Valid GenreRequestDTO dto) {
        return ResponseEntity.status(201).body(genreService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar gênero por ID", description = "Retorna um gênero específico")
    @ApiResponse(responseCode = "200", description = "Gênero encontrado")
    @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<GenreResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.findById(id));
    }

    @GetMapping()
    @Operation(summary = "Buscar todos os gêneros", description = "Retorna uma lista paginada de gêneros")
    @ApiResponse(responseCode = "200", description = "Lista de gêneros encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<?> findAllPageable(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(genreService.findAllPageable(page, size));
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar gêneros por nome", description = "Retorna uma lista paginada de gêneros filtrados pelo nome")
    @ApiResponse(responseCode = "200", description = "Lista de gêneros encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Page<GenreResponseDTO>> findByNameContainingIgnoreCase(@RequestParam String name,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok().body(genreService.findByNameContainingIgnoreCase(name, page, size));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar gênero", description = "Atualiza um gênero existente")
    @ApiResponse(responseCode = "200", description = "Gênero atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
    @ApiResponse(responseCode = "400", description = "Erro de validação")
    @ApiResponse(responseCode = "409", description = "Gênero já existe com esse nome")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<GenreResponseDTO> update(@PathVariable Long id,
                                                   @RequestBody @Valid GenreUpdateDTO dto) {
        return ResponseEntity.ok(genreService.update(id, dto));
    }

}
