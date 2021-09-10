package com.kata.maitred.use_case;

import com.kata.maitred.domain.*;

public class Reserver {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public Reserver(TableRepository tableRepository, ReservationRepository reservationRepository) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    public Outcome execute(int nombreDePersonnes) {
        final Table table = tableRepository.find();
        if (table.capaciteDeLaTable() >= nombreDePersonnes) {
            return new Accepted();
        }
        return null;
    }
}
