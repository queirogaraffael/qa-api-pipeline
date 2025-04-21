package com.example.cinema.api.repositories;

import com.example.cinema.api.entities.MovieSession;
import com.example.cinema.api.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {


    boolean existsByCinemaRoomAndStartTimeLessThanAndEndTimeGreaterThan(
            Room cinemaRoom,
            LocalDateTime newEndTime,
            LocalDateTime newStartTime
    );


}
