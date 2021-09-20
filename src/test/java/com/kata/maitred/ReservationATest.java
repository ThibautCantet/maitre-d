package com.kata.maitred;

import com.kata.maitred.domain.*;
import com.kata.maitred.infrastructure.InMemoryReservationRepository;
import com.kata.maitred.infrastructure.InMemoryTableRepository;
import com.kata.maitred.use_case.Reserver;
import io.cucumber.java.fr.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ReservationATest {

    private int nombreDePersonnes;
    private Outcome outcome;
    private final TableRepository tableRepository = new InMemoryTableRepository();
    private final ReservationRepository reservationRepository = new InMemoryReservationRepository();

    @Etantdonné("Une demande de réservation pour {int} personne\\(s)")
    public void uneDemandeDeRéservationPourPersonneS(int nombreDePersonnes) {
        this.nombreDePersonnes = nombreDePersonnes;
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
        outcome = reserver.execute(nombreDePersonnes);
    }

    @Alors("la réservation est validée")
    public void laRéservationEstValidée() {
        assertThat(outcome).isEqualTo(new Accepted());
    }

    @Alors("la réservation est refusée")
    public void laRéservationEstRefusée() {
        assertThat(outcome).isEqualTo(new Rejected());

    }
}
