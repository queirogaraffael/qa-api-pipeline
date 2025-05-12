package com.example.cinema.api.repositories;

import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {

    @Query("""
                SELECT COUNT(ms) > 0
                FROM MovieSession ms
                WHERE ms.cinemaRoom.id = :roomId
                  AND ms.showDate = :showDate
                  AND ms.startTime < :endTime
                  AND ms.endTime > :startTime
                  AND ms.canceled = false
            """)
    boolean existsSessionConflict(
            @Param("roomId") Long roomId,
            @Param("showDate") LocalDate showDate,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime
    );
}
