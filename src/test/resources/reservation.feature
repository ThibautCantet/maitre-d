# language: fr
Fonctionnalité: Réserver une table pour un nombre de personnes donné et une date donnée

  Scénario: Réservation possible car 0 réservation existante
    Etant donné Une demande de réservation pour "1" personne(s) le "2021/09/10"
    Et qu'il n'y a aucune réservation
    Quand on tente faire une réservation
    Alors la réservation est validée