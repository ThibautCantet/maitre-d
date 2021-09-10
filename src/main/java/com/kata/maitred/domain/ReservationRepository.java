package com.kata.maitred.domain;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> findAll();
}
