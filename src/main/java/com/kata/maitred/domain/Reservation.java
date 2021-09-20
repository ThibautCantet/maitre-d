package com.kata.maitred.domain;

import java.time.LocalDate;

public record Reservation(int nombreDePersonnes, LocalDate dateDeReservation) {
}
