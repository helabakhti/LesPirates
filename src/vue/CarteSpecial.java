package vue;

import java.util.Random;

class CarteSpeciale extends CartePopularite {
    private int perteVie;
    private TypeCarteSpeciale type;

    public CarteSpeciale(String nom, String description, int numCarte, int ptPopularite, int perteVie, TypeCarteSpeciale type) {
        super(nom, description, numCarte, ptPopularite);
        this.perteVie = perteVie;
        this.type = type;
    }

    @Override
    public void appliquerEffet(Joueur joueur, Joueur adversaire) {
        if (type == TypeCarteSpeciale.HEROS_TRAITRE) {
            // Effet Héros Traître : Vole 2 points et perd 1 point de vie
            if (adversaire.getPopularite() >= ptPopularite) {
                adversaire.ajouterPopularite(-ptPopularite);
                joueur.ajouterPopularite(ptPopularite);
            } else {
                joueur.ajouterPopularite(adversaire.getPopularite());
                adversaire.setPopularite(0);
            }
            joueur.retirerVie(perteVie);
        } else if (type == TypeCarteSpeciale.TRESOR_MAUDIT) {
            // Effet Trésor Maudit : Vole 2 pop 
            if (adversaire.getPopularite() >= ptPopularite) {
                adversaire.ajouterPopularite(-ptPopularite);
                joueur.ajouterPopularite(ptPopularite);
            } else {
                joueur.ajouterPopularite(adversaire.getPopularite());
                adversaire.setPopularite(0);
            }
        }
        // Effet Sabotage et chanage - L'adversaire perd une carte aléatoire
        else if (type == TypeCarteSpeciale.CHANTAGE || type == TypeCarteSpeciale.SABOTAGE ) {
            // Vérifier si l'adversaire a des cartes à perdre
            if (!adversaire.getMain().isEmpty()) {
                // Sélectionner une carte aléatoire dans la main de l'adversaire
                Random random = new Random();
                int indexAleatoire = random.nextInt(adversaire.getMain().size());
                
                // Retirer cette carte de la main de l'adversaire
                Cartes cartePerdue = adversaire.getMain().remove(indexAleatoire);
                System.out.println(adversaire.getNom() + " perd la carte : " + cartePerdue.getNomCarte());
            } else {
                System.out.println(adversaire.getNom() + " n'a pas de cartes à perdre.");
            }
        }
    }
}