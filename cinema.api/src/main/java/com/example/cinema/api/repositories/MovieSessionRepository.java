package com.example.cinema.api.repositories;

import com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO;
import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {


    boolean existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(
            Room cinemaRoom,
            LocalDateTime newEndTime,
            LocalDateTime newStartTime
    );

    @Query("SELECT new com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO(ms.id, ms.startTime, ms.endTime, ms.availableUntil, ms.basePrice, ms.status, ms.cinemaRoom.id, ms.movie.id) "
            + "FROM MovieSession ms JOIN ms.cinemaRoom r JOIN ms.movie m WHERE ms.id = :id")
    Optional<MovieSessionResponseDTO> findMovieSessionDtoById(Long id);


    @Query(
            value = "SELECT new com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO(" +
                    "ms.id, ms.startTime, ms.endTime, ms.availableUntil, ms.basePrice, ms.status, " +
                    "ms.cinemaRoom.id, ms.movie.id) " +
                    "FROM MovieSession ms " +
                    "WHERE ms.status IN ('SCHEDULED', 'ACTIVE') AND ms.availableUntil > :now",
            countQuery = "SELECT count(ms) FROM MovieSession ms WHERE ms.status IN ('SCHEDULED', 'ACTIVE') AND ms.availableUntil > :now"
    )
    Page<MovieSessionResponseDTO> findAllDisponiveisPaginado(@Param("now") LocalDateTime now, Pageable pageable);


    @Query(
            value = "SELECT new com.example.cinema.api.dtos.movieSession.MovieSessionResponseDTO(ms.id, ms.startTime, ms.endTime, ms.availableUntil, ms.basePrice, ms.status, ms.cinemaRoom.id, ms.movie.id) " +
                    "FROM MovieSession ms WHERE ms.cinemaRoom.id = :roomId",
            countQuery = "SELECT count(ms) FROM MovieSession ms WHERE ms.cinemaRoom.id = :roomId"
    )
    Page<MovieSessionResponseDTO> findAllByRoomId(@Param("roomId") Long roomId, Pageable pageable);

}
