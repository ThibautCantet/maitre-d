package com.kata.maitred.domain;

public class Table {
    private final int capacite;

    public Table (int capaciteDeLaTable) {
        this.capacite = capaciteDeLaTable;
    }

    public boolean verifierDisponibilite(int nombreDePlacesDejaReservees, int nombreDePersonnesDansLaReservation) {
        return capacite >= nombreDePlacesDejaReservees + nombreDePersonnesDansLaReservation;
    }
}
