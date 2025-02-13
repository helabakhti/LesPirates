package pirate;

public class Affichage {
	public String afficherBienvenu() {
		return("bienvenu dans le jeux des pirates");
	}
	
	public static void main(String[] args) {
		Affichage affichage = new Affichage();
		System.out.println(affichage.afficherBienvenu());
	}
}
 