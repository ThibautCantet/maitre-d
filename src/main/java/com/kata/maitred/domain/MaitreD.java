package com.kata.maitred.domain;

import java.util.List;

public class MaitreD {
    public Outcome reserver(String reservationId, List<Table> tables, List<Reservation> reservations, int nombreDePersonnesDansLaReservation) {
        final Integer nombreDePlacesDejaReservees = calculerLeNombreDePlacesDejaReservees(reservations);
        final boolean isTableDisponible = tables.stream().anyMatch(table -> table.verifierDisponibilite(nombreDePlacesDejaReservees, nombreDePersonnesDansLaReservation));
        if (isTableDisponible) {
            return new Accepted(reservationId);
        } else {
            return new Rejected();
        }
    }

    private Integer calculerLeNombreDePlacesDejaReservees(List<Reservation> reservations) {
        return reservations.stream()
                .map(Reservation::nombreDePersonnes)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
