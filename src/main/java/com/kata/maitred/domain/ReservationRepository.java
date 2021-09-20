package com.kata.maitred.domain;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {
    List<Reservation> findByDate(LocalDate reservationDate);

    void save(Reservation reservation);
}
