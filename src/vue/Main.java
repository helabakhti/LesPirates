package vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n Bienvenue dans le jeu des pirates !\n");

        // Création des joueurs
        Joueur joueur1 = new Joueur("Alice");
        Joueur joueur2 = new Joueur("Bob");

        // Création de plusieurs exemplaires des cartes pour éviter les répétitions constantes
        List<Cartes> cartesDisponibles = new ArrayList<>();
        cartesDisponibles.add(new CarteSpeciale("Héros Traître", "Vole 2 popularité, mais perd 1 vie pour sa trahison", 1, 2, 1, TypeCarteSpeciale.HEROS_TRAITRE));
        cartesDisponibles.add(new CarteSpeciale("Trésor Maudit", "Vole 2 popularité", 2, 2, 0, TypeCarteSpeciale.TRESOR_MAUDIT));
        cartesDisponibles.add(new CartePopularite("Abordage Réussi", " +2 Popularité", 3, 2));
        cartesDisponibles.add(new CarteAttaque("Coup de sabre", " -2 Vie", 4, 2));
        cartesDisponibles.add(new CarteDefense("Évasion Spectaculaire", "Ignore une attaque et gagne 1 popularité", 5, 1));
        cartesDisponibles.add(new CarteAttaque("Canon en Feu", "-3 Vie mais -1 vie pour le joueur aussi ", 5, 3));
        cartesDisponibles.add(new CarteSpeciale("Sabotage", "L'adversaire perd une carte aléatoire", 6, 0, 0, TypeCarteSpeciale.SABOTAGE));
        cartesDisponibles.add(new CartePopularite("Discours de Capitaine", " +2 Popularité", 7, 2));
        cartesDisponibles.add(new CartePopularite("Main de fer", "+3 Popularité", 8, 3));
        cartesDisponibles.add(new CarteDefense("Bouclier Magique", "protection du prochain coup et regagne 1 vie", 9, 1));
        cartesDisponibles.add(new CarteAttaque("Tempête de Sable", "Inflige -3 Vie et empêche de jouer une carte au prochain tour", 10, 3));
        cartesDisponibles.add(new CartePopularite("Charisme Naturel", "+3 Popularité", 11, 3));
        cartesDisponibles.add(new CarteAttaque("Tornade Dévastatrice", "-3 Vie mais perd 1 popularité", 12, 5));
        cartesDisponibles.add(new CarteSpeciale("Chantage", "L'adversaire perd une carte", 17, 0, 0, TypeCarteSpeciale.CHANTAGE));





        // Distribution des cartes dans la pioche des joueurs
        for (int i = 0; i < 3; i++) {  // 3 copies de chaque carte pour avoir une pioche riche
            for (Cartes carte : cartesDisponibles) {
                joueur1.ajouterCarteDansPioche(carte);
                joueur2.ajouterCarteDansPioche(carte);
            }
        }

        joueur1.melangerPioche();
        joueur2.melangerPioche();

        // Chaque joueur pioche 4 cartes de départ
        for (int i = 0; i < 4; i++) {
            joueur1.piocherCarte();
            joueur2.piocherCarte();
        }

        System.out.println("✅ Alice et Bob ont reçu 4 cartes de départ !");

        // Boucle de jeu
        Joueur joueurActuel = joueur1;
        Joueur adversaire = joueur2;

        while (!joueur1.aGagne() && !joueur2.aGagne() && !joueur1.estElimine() && !joueur2.estElimine()) {
            System.out.println("\n Tour de " + joueurActuel.getNom());

            // Compléter la main à 5 cartes si nécessaire
            while (joueurActuel.getMain().size() < 5) {
                joueurActuel.piocherCarte();
            }

            joueurActuel.afficherEtat();
            adversaire.afficherEtat();
            joueurActuel.afficherMain();

            if (joueurActuel.getMain().isEmpty()) {
                System.out.println(joueurActuel.getNom() + " n'a plus de cartes !");
            } else {
                int choix = joueurActuel.choisirCarte();
                if (choix != -1) {
                    joueurActuel.getMain().get(choix).appliquerEffet(joueurActuel, adversaire);
                    joueurActuel.retirerCarteJouee(choix);
                }
            }

            // Vérification de la victoire ou élimination
            if (joueurActuel.aGagne()) {
                System.out.println("\n" + joueurActuel.getNom() + " a gagné avec 5 points de popularité !");
                break;
            }
            if (adversaire.estElimine()) {
                System.out.println("\n  " + adversaire.getNom() + " a été éliminé !");
                break;
            }

            // Changer de joueur
            Joueur temp = joueurActuel;
            joueurActuel = adversaire;
            adversaire = temp;
        }

        System.out.println("\n Fin du jeu !");
        scanner.close();
    }
}
