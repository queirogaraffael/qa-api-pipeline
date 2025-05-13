package com.example.cinema.api.dtos.user;

import com.example.cinema.api.enums.UserCategory;
import com.example.cinema.api.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String dataJoined;
    private LocalDate birthdate;
    private UserRole role;
    private UserCategory category;
}
