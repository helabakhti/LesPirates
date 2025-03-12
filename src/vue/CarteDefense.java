package vue;

public class CarteDefense extends Cartes {

    private int valeur;

	public CarteDefense(String nom, String description, int numCarte, int valeur) {
        super(nom, description, numCarte);
        this.valeur=valeur;
    }

    @Override
    public void appliquerEffet(Joueur joueurActuel, Joueur adversaire) {
        System.out.println(" Defense ");
        joueurActuel.ajouterPopularite(1);
    }

	public int getValeur() {
		return valeur;
	}
}

