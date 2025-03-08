package vue;

import java.util.Random;

public class Jeu {
	private String[] cartes;
	private int i = 0;

	public Jeu() {
		initialiserCartes();
		melangerCartes();
	}

	public void initialiserCartes() {
		cartes = new String[] { "Abordage Réussi (+2 Popularité)", "Coup de sabre (-2 Vie)",
				"Révolte organisée (+1 Popularité)", "Main de Fer (+2 Popularité, -1 Vie)",
				"Discours inspirant (+1 Popularité)" };
	}

	public void melangerCartes() {
		Random rand = new Random();
		for (int i = cartes.length - 1; i > 0; i++) {
			int j = rand.nextInt(i + 1);
			String temp = cartes[i];
			cartes[i] = cartes[j];
			cartes[j] = temp;
		}
	}

	public void distribuerCartes(Joueur joueur) {
		if (i < cartes.length) {
			String carte = cartes[i++];
		}
	}

	// 5eme carte
	public String piocherCarte() {
		if (i < cartes.length) {
			return cartes[i++];
		} else {
			return null;
		}
	}

	public void jouer() {
		System.out.println("\nBienvenue dans le jeu des pirates !");
		Joueur joueur1 = new Joueur("Joueur Actuel");
		Joueur joueur2 = new Joueur("Adversaire");

		while (joueur1.getVie() > 0 && joueur2.getVie() > 0) {
			distribuerCartes(joueur1);
			distribuerCartes(joueur2);

			joueur1.afficherEtat();
			joueur2.afficherEtat();

			if (joueur1.aGagne()) {
				System.out.println(joueur1.getNom() + " a gagné !");
				break;
			}
			if (joueur2.aGagne()) {
				System.out.println(joueur2.getNom() + " a gagné !");
				break;
			}
		}
	}

}