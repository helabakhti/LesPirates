package vue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Joueur {
    private String nom;
    private int vie;
    private int popularite;
    private List<Cartes> main; 
    private List<Cartes> pioche; 
    private static Scanner scanner = new Scanner(System.in);

    public Joueur(String nom) {
        this.nom = nom;
        this.vie = 5;
        this.popularite = 0;
        this.main = new ArrayList<>();
        this.pioche = new ArrayList<>();
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }
    

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getPopularite() {
        return popularite;
    }

    public void setPopularite(int popularite) {
        this.popularite = popularite;
    }

    public List<Cartes> getMain() {
        return main;
    }

    public void ajouterPopularite(int points) {
        this.popularite += points;
    }

    public void retirerVie(int points) {
        this.vie -= points;
    }
    
    public void ajouterVie(int points) {
        this.vie += points; 	
    }

    public void afficherEtat() {
        System.out.println(nom + " : " + vie + " vie , " + popularite + " Popularité");
    }

    // Vérifie si le joueur a gagné
    public boolean aGagne() {
        return popularite >= 5;
    }

    public boolean estElimine() {
        return vie <= 0;
    }

    // Ajouter une carte à la main (max 5 cartes)
    public void ajouterCarteDansMain(Cartes carte) {
        if (main.size() < 5) {
            main.add(carte);
        } else {
            System.out.println(" La main de " + nom + " est pleine ");
        }
    }

    // Ajouter une carte dans la pioche
    public void ajouterCarteDansPioche(Cartes carte) {
        pioche.add(carte);
    }

    // Mélanger la pioche
    public void melangerPioche() {
        Collections.shuffle(pioche);
    }

    // Piocher une carte pour compléter la main (5 cartes max)
    public void piocherCarte() {
        if (!pioche.isEmpty() && main.size() < 5) {
            main.add(pioche.remove(0)); 
        } else {
            System.out.println("pioche vide");
        }
    }

    // Retirer une carte de la main après l'avoir jouée
    public void retirerCarteJouee(int index) {
        if (index < 0 || index >= main.size()) {
            System.out.println("Index invalide !");
            return;
        }
        main.remove(index);
    }

    // Affichage des cartes en main
    public void afficherMain() {
        if (main.isEmpty()) {
            System.out.println(nom + " n'a aucune carte en main");
        } else {
            System.out.println("\n Main de " + nom + " :");
            for (int i = 0; i < main.size(); i++) {
                System.out.println((i + 1) + ". " + main.get(i).getNomCarte() + " - " + main.get(i).getDescriptionCarte());
            }
        }
    }

    // choisir une carte à jouer
    public int choisirCarte() {
        if (main.isEmpty()) {
            System.out.println(nom + " n'a pas de cartes à jouer");
            return -1;
        }

        int numCarte;
        do {
            System.out.print(nom + ", choisissez une carte à jouer (1-" + main.size() + ") : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
            numCarte = scanner.nextInt();
        } while (numCarte < 1 || numCarte > main.size());

        return numCarte - 1; 
    }

   

}
