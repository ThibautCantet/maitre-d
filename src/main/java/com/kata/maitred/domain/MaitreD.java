package com.kata.maitred.domain;

import java.util.List;

public class MaitreD {
    public Outcome reserver(Table table, List<Reservation> reservations, int nombreDePersonnesDansLaReservation) {
        final Integer nombreDePlacesDejaReservees = calculerLeNombreDePlacesDejaReservees(reservations);
        if (table.verifierDisponibilite(nombreDePlacesDejaReservees, nombreDePersonnesDansLaReservation)) {
            return new Accepted();
        }
        return new Rejected();
    }

    private Integer calculerLeNombreDePlacesDejaReservees(List<Reservation> reservations) {
        return reservations.stream()
                .map(Reservation::nombreDePersonnes)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
