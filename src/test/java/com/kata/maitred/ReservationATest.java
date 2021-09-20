package com.kata.maitred;

import com.kata.maitred.domain.*;
import com.kata.maitred.infrastructure.InMemoryReservationRepository;
import com.kata.maitred.infrastructure.InMemoryTableRepository;
import com.kata.maitred.use_case.Reserver;
import io.cucumber.java.fr.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationATest {
    private int nombreDePersonnes;

    private Outcome outcome;
    private final TableRepository tableRepository = new InMemoryTableRepository();
    private final ReservationRepository reservationRepository = new InMemoryReservationRepository();
    private LocalDate reservationDate;

    @Etantdonné("Une demande de réservation pour {int} personne\\(s) pour le {string}")
    public void uneDemandeDeRéservationPourPersonneSPourLe(int nombreDePersonnes, String date) {
        this.nombreDePersonnes = nombreDePersonnes;
        this.reservationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Etantdonné("Une demande de réservation pour {int} personne\\(s) pour aujourd'hui")
    public void uneDemandeDeRéservationPourPersonneS(int nombreDePersonnes) {
        this.nombreDePersonnes = nombreDePersonnes;
        this.reservationDate = LocalDate.now();
    }

    @Etque("le restaurant contient une table de {int} places")
    public void leRestaurantContientUneTableDePlaces(int capaciteDeLaTable) {
        ((InMemoryTableRepository) tableRepository).clear();
        tableRepository.save(new Table(capaciteDeLaTable));
    }

    @Etqu("il n'y a aucune réservation")
    public void ilNYAAucuneRéservation() {
    }

    @Quand("on tente faire une réservation")
    public void onTenteFaireUneRéservation() {
        final Reserver reserver = new Reserver(tableRepository, reservationRepository);
        outcome = reserver.execute(nombreDePersonnes, reservationDate);
    }

    @Alors("la réservation est validée")
    public void laRéservationEstValidée() {
        assertThat(outcome).isEqualTo(new Accepted());
    }

    @Alors("la réservation est refusée")
    public void laRéservationEstRefusée() {
        assertThat(outcome).isEqualTo(new Rejected());
    }

    @Etqu("il y a déjà une réservation de {int} places pour le {string}")
    public void ilYADéjàUneRéservationDePlacesPourLe(int nombreDePersonnes, String date) {
        final LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        reservationRepository.save(new Reservation(nombreDePersonnes, reservationDate));
    }

    @Et("la réservation de {int} personne\\(s) pour {string} est enregistrée")
    public void laRéservationDePersonneSEstEnregistrée(int nombreDePersonnes, String date) {
        LocalDate reservationDate = null;
        if (date.equals("aujourd'hui")) {
            reservationDate = LocalDate.now();
        }
        final List<Reservation> reservations = reservationRepository.findByDate(reservationDate);
        assertThat(reservations).containsExactly(new Reservation(nombreDePersonnes, reservationDate));
    }

    @Et("la réservation de {int} personne\\(s) pour {string} n'est pas enregistrée")
    public void laRéservationDePersonneSPourNEstPasEnregistrée(int nombreDePersonnes, String date) {
        LocalDate reservationDate = null;
        if (date.equals("aujourd'hui")) {
            reservationDate = LocalDate.now();
        } else {
            reservationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        final List<Reservation> reservations = reservationRepository.findByDate(reservationDate);
        assertThat(reservations).doesNotContain(new Reservation(nombreDePersonnes, reservationDate));
    }
}
