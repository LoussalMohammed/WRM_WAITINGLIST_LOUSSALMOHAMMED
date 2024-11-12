# WRM_WAITINGLIST_LOUSSALMOHAMMED
WRM - Gestion des Files d'Attentes
Contexte

De nombreuses organisations, notamment les cabinets médicaux, services publics et entreprises, nécessitent un moyen efficace de gérer les files d'attente pour une prise en charge ordonnée de leurs visiteurs. Le module WRM (Waiting Room Management) propose une solution centralisée pour :

    Organiser le flux de visiteurs
    Optimiser les priorités et ordonnancements en salle d'attente
    Suivre les indicateurs de performance tels que le taux de satisfaction et le temps moyen d'attente

Caractéristiques Principales

    Gestion Dynamique des Visiteurs
        Suivi de l'état des visiteurs : En attente, En cours, Terminé, Annulé
    Configuration d'Ordonnancement
        Supporte plusieurs algorithmes d'ordonnancement :
            FIFO : Premier arrivé, premier servi
            HPF : Priorité en fonction du type de visiteur ou de l'urgence
            SJF : Shortest Job First (priorité pour les tâches courtes)
    Personnalisation des Horaires et Capacité
        Spécification de la capacité maximale par jour et configuration des horaires de service (journée continue, matin, après-midi)
    Statistiques et Performance
        Génération de métriques de performance telles que le taux de satisfaction, le temps moyen d'attente, et la rotation des visiteurs pour améliorer la gestion des files d'attente

Entités
Visitor (Visiteur)

    id : Long - Identifiant unique
    name : String - Nom du visiteur
    arrivalTime : DateTime - Heure d'arrivée
    status : Enum - État du visiteur (WAITING, IN_PROGRESS, FINISHED, CANCELLED)
    priority : Integer - Niveau de priorité (utilisé pour l'algorithme HPF)
    estimatedProcessingTime : Integer - Durée estimée en minutes (utilisé pour l'algorithme SJF)
    startTime : DateTime - Heure du début du service
    endTime : DateTime - Heure de fin du service

WaitingRoom (Salle d’attente)

    id : Long - Identifiant unique
    date : Date - Date de la salle d'attente

Technologies Utilisées

    Spring Framework
        Spring Context
        Spring Data JPA
        Spring Web
    Mapping Dto : ModelMapper ou MapStruct
    Tests d'Intégration : Postman
    Gestion des Exceptions : @RestControllerAdvice
    Configuration : Fichier YAML, profils de Spring (dev, sit, uat, prod)
    Base de Données : MySQL, exécutée en conteneur Docker

Installation et Lancement

    Cloner le Repository

git clone https://github.com/yourusername/wrm-queue-management.git
cd wrm-queue-management

Configurer MySQL dans Docker

docker run --name mysql-wrm -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=wrm -p 3306:3306 -d mysql:latest

Configurer le Fichier Application YAML

    Dans src/main/resources/application.yml, configurez les informations de connexion à MySQL et les profils de développement.

Lancer l'Application

    ./mvnw spring-boot:run

Utilisation de l'API

Utilisez Postman pour tester les endpoints REST de l'API. Les principales opérations incluent :

    Gestion des Visiteurs : Ajout, mise à jour, changement d'état, et suppression de visiteurs
    Gestion des Salles d'Attente : Création, visualisation et configuration
    Statistiques : Accéder aux indicateurs de performance et analyser les métriques de la salle d'attente

Contribuer

Les contributions sont les bienvenues ! Pour toute demande de fonctionnalités ou rapport de bug, ouvrez une issue ou envoyez une pull request.
