package com.kata.maitred.domain;

import java.time.LocalDate;

public record Reservation(String id, int nombreDePersonnes, LocalDate dateDeReservation) {
}
