# language: fr
Fonctionnalité: Réserver une table pour un nombre de personnes donné et une date donnée

  Scénario: Réservation possible car 0 réservation existante
    Etant donné Une demande de réservation pour 1 personne(s) pour aujourd'hui
    Et que le restaurant contient une table de 12 places
    Et qu'il n'y a aucune réservation
    Quand on tente faire une réservation
    Alors la réservation est validée
    Et la réservation de 1 personne(s) pour "aujourd'hui" est enregistrée

  Scénario: Réservation impossible car le nombre de personne est supérieur à la capacité de la table
    Etant donné Une demande de réservation pour 13 personne(s) pour aujourd'hui
    Et que le restaurant contient une table de 12 places
    Et qu'il n'y a aucune réservation
    Quand on tente faire une réservation
    Alors la réservation est refusée
    Et la réservation de 1 personne(s) pour "aujourd'hui" n'est pas enregistrée

  Scénario: Réservation possible car 0 réservation existante
    Etant donné Une demande de réservation pour 12 personne(s) pour aujourd'hui
    Et que le restaurant contient une table de 12 places
    Et qu'il n'y a aucune réservation
    Quand on tente faire une réservation
    Alors la réservation est validée

  Scénario: Réservation impossible car plus de place disponible pour la date donnée
    Etant donné Une demande de réservation pour 3 personne(s) pour le "2023-09-14"
    Et que le restaurant contient une table de 4 places
    Et qu'il y a déjà une réservation de 3 places pour le "2023-09-14"
    Quand on tente faire une réservation
    Alors la réservation est refusée
    Et la réservation de 3 personne(s) pour "2023-09-14" n'est pas enregistrée

  Scénario: Réservation possible car assez de places disponibles pour la date donnée
    Etant donné Une demande de réservation pour 3 personne(s) pour le "2023-09-14"
    Et que le restaurant contient une table de 10 places
    Et qu'il y a déjà une réservation de 2 places pour le "2023-09-14"
    Quand on tente faire une réservation
    Alors la réservation est validée
    Et la réservation de 3 personne(s) pour "2023-09-14" est enregistrée