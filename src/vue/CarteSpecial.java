package vue;

import java.util.Random;

class CarteSpeciale extends Cartes {
    private int perteVie;
    private int ptPopularite;
    private TypeCarteSpeciale type;

    public CarteSpeciale(String nom, String description, int numCarte, int ptPopularite, int perteVie, TypeCarteSpeciale type) {
        super(nom, description, numCarte);
        this.perteVie = perteVie;
        this.ptPopularite = ptPopularite;
        this.type = type;
    }

    @Override
    public void appliquerEffet(Joueur joueur, Joueur adversaire) {
        if (type == TypeCarteSpeciale.HEROS_TRAITRE) {
        	
            // Héros Traître : Vole 2 points et perd 1 point de vie pour sa trahison
            if (adversaire.getPopularite() >= ptPopularite) {
                adversaire.ajouterPopularite(-ptPopularite);
                joueur.ajouterPopularite(ptPopularite);
            } else {
                joueur.ajouterPopularite(adversaire.getPopularite());
                adversaire.setPopularite(0);
            }
            joueur.retirerVie(perteVie);
        } else if (type == TypeCarteSpeciale.TRESOR_MAUDIT) {
            //Trésor Maudit : Vole 2 popularité
            if (adversaire.getPopularite() >= ptPopularite) {
                adversaire.ajouterPopularite(-ptPopularite);
                joueur.ajouterPopularite(ptPopularite);
            } else {
                joueur.ajouterPopularite(adversaire.getPopularite());
                adversaire.setPopularite(0);
            }
        }
        
        //Sabotage et Chantage : L'adversaire perd une carte aléatoire
        else if (type == TypeCarteSpeciale.CHANTAGE || type == TypeCarteSpeciale.SABOTAGE) {
            if (adversaire == null) {
                System.out.println("Erreur : adversaire est null !");
                return;
            }

            if (adversaire.getMain() == null || adversaire.getMain().isEmpty()) {
                System.out.println(adversaire.getNom() + " n'a pas de cartes à perdre");
                return;
            }

            // selectionner une carte aléatoire dans la main de l'adversaire et le retirer
            Random random = new Random();
            int indexAleatoire = random.nextInt(adversaire.getMain().size());
            System.out.println("Index aléatoire sélectionné : " + indexAleatoire);

     
            Cartes cartePerdue = adversaire.getMain().remove(indexAleatoire);
            
            if (cartePerdue != null) {
                System.out.println(adversaire.getNom() + " perd la carte : " + cartePerdue.getNomCarte());
            } else {
                System.out.println("Erreur");
            }
        }
    }
}
