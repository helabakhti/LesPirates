package vue;
import java.util.ArrayList;
import java.util.Collections;

public class Pioche {
    private ArrayList<Cartes> cartes;

    public Pioche() {
        cartes = new ArrayList<>();
    }

    // Ajouter une carte à la pioche
    public void ajouterCarte(Cartes carte) {
        cartes.add(carte);
    }

    // Mélanger la pioche avant de commencer le jeu
    public void melanger() {
        Collections.shuffle(cartes);
    }

    // Piocher une carte (retirer une carte du deck)
    public Cartes piocherCarte() {
        if (!cartes.isEmpty()) {
            return cartes.remove(0); // Prend la première carte de la pioche
        } else {
            System.out.println("⚠️ La pioche est vide !");
            return null;
        }
    }

    // Vérifier si la pioche est vide
    public boolean estVide() {
        return cartes.isEmpty();
    }
}
