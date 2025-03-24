package vue;

class CartePopularite extends Cartes {
    public int ptPopularite;

    public CartePopularite(String nom, String description, int numCarte, int ptPopularite) {
        super(nom, description, numCarte);
        this.ptPopularite = ptPopularite;
    }

    @Override
    public void appliquerEffet(Joueur joueur, Joueur adversaire) {
        joueur.ajouterPopularite(ptPopularite);
    }
}
