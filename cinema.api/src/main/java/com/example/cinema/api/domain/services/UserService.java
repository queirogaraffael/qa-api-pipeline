package com.example.cinema.api.domain.services;

import com.example.cinema.api.domain.entities.User;
import com.example.cinema.api.domain.repositories.UserRepository;
import com.example.cinema.api.domain.user.factories.UserFactory;
import com.example.cinema.api.shared.dtos.user.UserCreatedResponseDTO;
import com.example.cinema.api.shared.dtos.user.UserRequestDTO;
import com.example.cinema.api.shared.exceptions.UserAlreadyExistsException;
import com.example.cinema.api.shared.exceptions.UserNotAuthenticatedException;
import com.example.cinema.api.shared.mappers.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserCreatedResponseDTO createUser(UserRequestDTO data) {

        if (userRepository.existsByUsername(data.getUsername())) {
            throw new UserAlreadyExistsException("Usuário já existe");
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        User newUser = UserFactory.createFromDto(data, encryptedPassword);

        User user = userRepository.save(newUser);

        return userMapper.toResponseDTO(user);

    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new UserNotAuthenticatedException("Usuário não autenticado");
        }

        return (User) authentication.getPrincipal();
    }

}
