package com.kata.maitred.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository {
    String next();

    List<Reservation> findByDate(LocalDate reservationDate);

    void save(Reservation reservation);
}
