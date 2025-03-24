package vue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Pioche {
    private Cartes[] cartes; // tableau de cartes
    private int position;    // Indice de la prochaine carte 
    private int taille;      // Nombre des cartes dans la pioche

    public Pioche(int capaciteMax) {
        this.cartes = new Cartes[capaciteMax];
        this.position = 0;
        this.taille = 0;
    }

    // Ajouter une carte à la pioche 
    public void ajouterCarte(Cartes carte) {
        if (carte == null) {
            System.err.println("erreur:Tentative d'ajout d'une carte nulle ");
            return;
        }
        if (taille < cartes.length) {
            cartes[taille] = carte;
            taille++;
        } else {
            System.err.println("erreur: La pioche est pleine, impossible d'ajouter plus de cartes");
        }
    }

    // melanger la pioche
    public void melanger() {
        // Transformer en liste puis le remettre en tableau
        List<Cartes> listeTemp = Arrays.asList(Arrays.copyOf(cartes, taille));
        //mélanger aléatoirement
        Collections.shuffle(listeTemp);
        cartes = listeTemp.toArray(new Cartes[0]);
        position = 0; //
    }

    // Piocher une carte
    public Cartes piocherCarte() {
        if (position < taille) {
            return cartes[position++]; // Retourne la carte actuelle et incrémente 
        } else {
            System.err.println("La pioche est vide !");
            return null;
        }
    }

    // Vérifier si la pioche est vide
    public boolean estVide() {
        return position >= taille;
    }

    public int taille() {
        return taille - position; // Cartes restantes à piocher
    }
}
