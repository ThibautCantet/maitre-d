package com.kata.maitred;

import com.kata.maitred.domain.*;
import com.kata.maitred.infrastructure.InMemoryReservationRepository;
import com.kata.maitred.infrastructure.InMemoryTableRepository;
import com.kata.maitred.use_case.Reserver;
import io.cucumber.java.fr.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationATest {
    public static final String ID_RESERVATION_EXISTANTE = "0cf3c871-9e6c-4050-be0d-da6bbbbc0f76";
    private int nombreDePersonnes;

    private Outcome outcome;
    private final TableRepository tableRepository = new InMemoryTableRepository();
    private final UUID fixedReservationId = UUID.randomUUID();
    private final ReservationRepository reservationRepository = new InMemoryReservationRepository(fixedReservationId);
    private LocalDate reservationDate;
    private final List<Reservation> reservationsExistantes = new ArrayList<>();

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
        assertThat(outcome).isEqualTo(new Accepted(fixedReservationId.toString()));
    }

    @Alors("la réservation est refusée")
    public void laRéservationEstRefusée() {
        assertThat(outcome).isEqualTo(new Rejected());
    }

    @Etqu("il y a déjà une réservation de {int} places pour le {string}")
    public void ilYADéjàUneRéservationDePlacesPourLe(int nombreDePersonnes, String date) {
        final LocalDate reservationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Reservation reservationExistante = new Reservation(ID_RESERVATION_EXISTANTE, nombreDePersonnes, reservationDate);
        reservationRepository.save(reservationExistante);
        reservationsExistantes.add(reservationExistante);
    }

    @Et("la réservation de {int} personne\\(s) pour {string} est enregistrée")
    public void laRéservationDePersonneSEstEnregistrée(int nombreDePersonnes, String date) {
        LocalDate reservationDate = null;
        if (date.equals("aujourd'hui")) {
            reservationDate = LocalDate.now();
        } else {
            reservationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        final List<Reservation> reservations = reservationRepository.findByDate(reservationDate);
        final Reservation nouvelleReservationAcceptee = new Reservation(((Accepted) outcome).reservationId(), nombreDePersonnes, reservationDate);
        reservationsExistantes.add(nouvelleReservationAcceptee);
        assertThat(reservations).isEqualTo(reservationsExistantes);
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
        assertThat(reservations).isEqualTo(reservationsExistantes);
    }
}
