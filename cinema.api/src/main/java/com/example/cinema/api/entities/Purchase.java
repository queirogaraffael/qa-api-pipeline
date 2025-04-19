package com.example.cinema.api.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    private Long id;
    private LocalDateTime purchaseDate;
    private double totalPrice;

    private User user;
    private Ticket ticket;
    private Session session;

}
