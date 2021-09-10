package com.kata.maitred.infrastructure;

import com.kata.maitred.domain.Reservation;
import com.kata.maitred.domain.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepository implements ReservationRepository {
    private List<Reservation> reservations = new ArrayList<>();

    @Override
    public List<Reservation> findAll() {
        return reservations;
    }
}
