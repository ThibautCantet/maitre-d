package com.kata.maitred.infrastructure;

import com.kata.maitred.domain.Reservation;
import com.kata.maitred.domain.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class InMemoryReservationRepository implements ReservationRepository {
    private final List<Reservation> reservations = new ArrayList<>();
    private final UUID uuid;

    public InMemoryReservationRepository(UUID fixedUUID) {
        uuid = fixedUUID;
    }

    public InMemoryReservationRepository() {
        uuid = UUID.randomUUID();
    }

    @Override
    public String next() {
        return uuid.toString();
    }

    @Override
    public List<Reservation> findByDate(LocalDate reservationDate) {
        return reservations.stream()
                .filter(reservation -> reservation.dateDeReservation().equals(reservationDate))
                .collect(toList());
    }

    @Override
    public void save(Reservation reservation) {
        reservations.add(reservation);
    }
}
