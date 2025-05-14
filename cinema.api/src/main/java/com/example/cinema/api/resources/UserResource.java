package com.example.cinema.api.resources;

import com.example.cinema.api.domain.repositories.UserRepository;
import com.example.cinema.api.shared.dtos.user.UserRequestDTO;
import com.example.cinema.api.domain.entities.User;
import com.example.cinema.api.domain.user.factories.UserFactory;
import com.example.cinema.api.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserResource(UserService userService, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Criar um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "409", description = "Usuário já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/users")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequestDTO data) {
        if (userRepository.existsByUsername(data.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        User newUser = UserFactory.createFromDto(data, encryptedPassword);


        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
