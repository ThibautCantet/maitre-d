package com.kata.maitred.use_case;

import com.kata.maitred.domain.*;

import java.util.List;

public class Reserver {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public Reserver(TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    public Outcome execute(int nombreDePersonnesDeLaReservation) {
        final Table table = tableRepository.find();
        final MaitreD maitreD = new MaitreD();
        final List<Reservation> reservations = reservationRepository.findAll();
        return maitreD.reserver(table, reservations, nombreDePersonnesDeLaReservation);
    }
}
