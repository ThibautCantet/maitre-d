package com.kata.maitred.use_case;

import com.kata.maitred.domain.*;

import java.time.LocalDate;
import java.util.List;

public class Reserver {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public Reserver(TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    public Outcome execute(int nombreDePersonnesDeLaReservation, LocalDate reservationDate) {
        final Table table = tableRepository.find();
        final MaitreD maitreD = new MaitreD();
        final List<Reservation> reservations = reservationRepository.findByDate(reservationDate);
        final String reservationId = reservationRepository.next();
        final Outcome event = maitreD.reserver(reservationId, table, reservations, nombreDePersonnesDeLaReservation);
        if (event instanceof Accepted) {
            reservationRepository.save(new Reservation(reservationId, nombreDePersonnesDeLaReservation, reservationDate));
        }
        return event;
    }
}
