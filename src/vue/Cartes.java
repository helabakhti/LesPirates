package vue;

public abstract class Cartes {  
    protected String nom;
    protected String description;
    protected int numCarte;

    public Cartes(String nom, String description, int numCarte) {
        this.nom = nom;
        this.description = description;
        this.numCarte = numCarte;
    }

    public String getNomCarte() {
        return nom;
    }

    public int getNumeroCarte() {
        return numCarte;
    }

    public String getDescriptionCarte() {
        return description;
    }

    public abstract void appliquerEffet(Joueur joueur, Joueur adversaire);
}
