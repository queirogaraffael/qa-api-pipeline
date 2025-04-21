package com.example.cinema.api.services;

import com.example.cinema.api.repositories.SessionRepository;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    // Criar ->
    // Pegar por id
    // Pegar todas paginadas
    // Editar

    // Quartos associado a uma seção
    // tikcets/usuarios associados a uma seção
    // quais seções estão associadas aquele quarto ?
    // quais tikets/users estão associados aquela seção ?


}
