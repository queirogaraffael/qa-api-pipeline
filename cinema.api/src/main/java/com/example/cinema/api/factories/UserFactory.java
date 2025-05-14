package com.example.cinema.api.factories;

import com.example.cinema.api.dtos.user.UserRequestDTO;
import com.example.cinema.api.entities.User;

public class UserFactory {

    public static User createFromDto(UserRequestDTO dto, String encryptedPassword) {
        return new User(
                null,
                dto.getUsername(),
                dto.getName(),
                dto.getEmail(),
                encryptedPassword,
                dto.getDataJoined(),
                dto.getBirthdate(),
                dto.getRole(),
                dto.getCategory(),
                null,
                null
        );
    }

}
