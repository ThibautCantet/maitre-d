package com.kata.maitred;

import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Etqu;
import io.cucumber.java.fr.Quand;

public class ReservationATest {

    @Etantdonné("Une demande de réservation pour {string} personne\\(s) le {string}")
    public void uneDemandeDeRéservationPourPersonneSLe(String nombreDePersonnes, String date) {
    }

    @Etqu("il n'y a aucune réservation")
    public void ilNYAAucuneRéservation() {
    }

    @Quand("on tente faire une réservation")
    public void onTenteFaireUneRéservation() {
    }

    @Alors("la réservation est validée")
    public void laRéservationEstValidée() {
    }
}
