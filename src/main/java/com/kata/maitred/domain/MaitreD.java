package com.kata.maitred.domain;

import java.util.List;

public class MaitreD {
    public Outcome reserver(Table table, List<Reservation> reservations, int nombreDePersonnesDansLaReservation) {
        if (table.verifierDisponibilite(nombreDePersonnesDansLaReservation)) {
            return new Accepted();
        }
        return null;
    }
}
