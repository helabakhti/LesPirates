package vue;

import java.util.Scanner;

public class Afficheur {
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Afficheur afficheur = new Afficheur();
        afficheur.jouer();
    }

    public void afficherNomJoueur(String nomJoueur) {
        System.out.println("Le nom du joueur est " + nomJoueur);
    }

    public void afficherEtatJoueur(String nomJoueur, int numVie, int numPopularite) {
        System.out.println("L'état du joueur " + nomJoueur + " : " + numVie + " cœurs et " + numPopularite + " popularité ");
    }

    public void afficherCarte(String[] carte) {
        for (int i = 0; i < carte.length; i++) {
            System.out.println("La carte"+" "+ (i + 1) + " : " +  carte[i]);
        }
    }

    public int choisirCarte(int maxCartes) {
        int numCarte;
        do {
            System.out.println("Entrer le numéro de la carte à deposer: ");
            while (!scanner.hasNextInt()) {  
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); 
            }
            numCarte = scanner.nextInt();
        } while (numCarte < 1 || numCarte > maxCartes); 
        return numCarte;
    }

    private void jouer() {
        System.out.print("Entrez le nom du joueur : ");
        String nomJoueur = scanner.nextLine();

        // les cartes avec descriptions
        String[] carte = { "Abordage Réussi (+2 Popularité)", 
                           "Coup de sabre (-2 Vie)", 
                           "Révolte organisée (+1 Popularité)" ,
                           "Main de Fer (+2 Popularité, -1 Vie)" , 
                           "Discours Insirant (+1 Popularité)"};

        afficherNomJoueur(nomJoueur);
        afficherEtatJoueur(nomJoueur, 5, 0);
        afficherCarte(carte);

        // le joueur choisit une carte
        int numCarte = choisirCarte(carte.length);

        // description de la carte deposé
        System.out.println("Carte deposée : " + carte[numCarte - 1]);
    }
}
