package com.example.cinema.api.resources;

import com.example.cinema.api.dtos.user.UserRequestDTO;
import com.example.cinema.api.entities.User;
import com.example.cinema.api.infra.security.TokenService;
import com.example.cinema.api.repositories.UserRepository;
import com.example.cinema.api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/users")
public class UserResource {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Operation(summary = "Criar um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro ao criar usuário")
    @ApiResponse(responseCode = "401", description = "Não autorizado")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping()
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequestDTO data) {
        if (this.userService.loadUserByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(null, data.getUsername(), data.getName(), data.getEmail(), encryptedPassword, data.getDataJoined(),
                data.getBirthdate(), data.getRole(), data.getCategory(), null, null);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
