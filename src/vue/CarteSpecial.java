package vue;

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
            // Effet Trésor Maudit : Vole 2 points sans perte de vie
            if (adversaire.getPopularite() >= ptPopularite) {
                adversaire.ajouterPopularite(-ptPopularite);
                joueur.ajouterPopularite(ptPopularite);
            } else {
                joueur.ajouterPopularite(adversaire.getPopularite());
                adversaire.setPopularite(0);
            }
        }
    }
}
