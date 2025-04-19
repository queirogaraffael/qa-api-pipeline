package com.example.cinema.api.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private String description;
    private String releaseDate;
    private int duration;
    private String imageUrl;

    private Rating rating;
    private Genre genre;
}
