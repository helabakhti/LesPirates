package vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Afficheur {
	private Scanner scanner = new Scanner(System.in);

	private List<String> zonePopularite1 = new ArrayList<>();
	private List<String> zoneAttaque1 = new ArrayList<>();
	private List<String> zonePopularite2 = new ArrayList<>();
	private List<String> zoneAttaque2 = new ArrayList<>();

	private int numVie1 = 5;
	private int numPopularite1 = 0;
	private int numVie2 = 5;
	private int numPopularite2 = 0;

	private String nomJoueur1;
	private String nomJoueur2;

	public static void main(String[] args) {
		System.out.println("\n Bienvenu dans le jeu des pirates \n");

		Afficheur afficheur = new Afficheur();
		afficheur.jouer();
	}

	private void jouer() {

		System.out.print("Entrez le nom du Joueur 1 : ");
		nomJoueur1 = scanner.nextLine();
		System.out.print("Entrez le nom du Joueur 2 : ");
		nomJoueur2 = scanner.nextLine();

		String[] cartes = { "Abordage Réussi (+2 Popularité)", "Coup de sabre (-2 Vie)",
				"Révolte organisée (+1 Popularité)", "Main de Fer (+2 Popularité, -1 Vie)",
				"Discours Inspirant (+1 Popularité)" };

		while (numVie1 > 0 && numVie2 > 0) {
			if (tourJoueur(nomJoueur1, true, cartes))
				break;
			if (tourJoueur(nomJoueur2, false, cartes))
				break;
		}

	}

	private boolean tourJoueur(String nomJoueur, boolean estJoueur1, String[] cartes) {
		afficherEtat();

		System.out.println("\n Tour de " + nomJoueur);
		afficherCarte(cartes);

		int numCarte = choisirCarte(cartes.length);
		String carteChoisie = cartes[numCarte - 1];

		int choixZone = choisirZone();

		if (estJoueur1) {
			if (choixZone == 1) {
				zonePopularite1.add(carteChoisie);
				appliquerEffetCarte(carteChoisie, true, true);
			} else {
				zoneAttaque1.add(carteChoisie);
				appliquerEffetCarte(carteChoisie, false, true);
			}
		} else {
			if (choixZone == 1) {
				zonePopularite2.add(carteChoisie);
				appliquerEffetCarte(carteChoisie, true, false);
			} else {
				zoneAttaque2.add(carteChoisie);
				appliquerEffetCarte(carteChoisie, false, false);
			}
		}

		afficherZones();

		if (numVie1 <= 0) {
			System.out.println("\n " + nomJoueur2 + " a gagné ! ");
			return true;
		}
		if (numVie2 <= 0) {
			System.out.println("\n " + nomJoueur1 + " a gagné ! ");
			return true;
		}
		if (numPopularite1 >= 5) {
			System.out.println("\n " + nomJoueur1 + " a gagné ");
			return true;
		}
		if (numPopularite2 >= 5) {
			System.out.println("\n " + nomJoueur2 + " a gagné ");
			return true;
		}

		return false;
	}

	private void appliquerEffetCarte(String carte, boolean estPopularite, boolean estJoueur1) {
		if (estJoueur1) {
			if (carte.contains("+2 Popularité"))
				numPopularite1 += 2;
			if (carte.contains("+1 Popularité"))
				numPopularite1 += 1;
			if (carte.contains("-1 Vie"))
				numVie2 -= 1;
			if (carte.contains("-2 Vie"))
				numVie2 -= 2;
		} else {
			if (carte.contains("+2 Popularité"))
				numPopularite2 += 2;
			if (carte.contains("+1 Popularité"))
				numPopularite2 += 1;
			if (carte.contains("-1 Vie"))
				numVie1 -= 1;
			if (carte.contains("-2 Vie"))
				numVie1 -= 2;
		}
	}

	private void afficherEtat() {
		System.out.println("\n Etat des Joueurs ");
		System.out.println(nomJoueur1 + " : " + numVie1 + " coeurs et " + numPopularite1 + "  Popularité");
		System.out.println(nomJoueur2 + " : " + numVie2 + " coeurs et " + numPopularite2 + "  Popularité");
	}

	private void afficherCarte(String[] cartes) {

		System.out.println("\n Les cartes piochées: le joueur a 5 cartes à la main");
		for (int i = 0; i < cartes.length; i++) {
			System.out.println("Carte " + (i + 1) + " - " + cartes[i]);
		}
	}

	private int choisirCarte(int maxCartes) {
		int numCarte;
		do {
			System.out.print("Choisir une carte à déposer : ");
			while (!scanner.hasNextInt()) {
				System.out.println("Veuillez entrer un nombre valide.");
				scanner.next();
			}
			numCarte = scanner.nextInt();
		} while (numCarte < 1 || numCarte > maxCartes);
		return numCarte;
	}

	private int choisirZone() {
		int choix;
		do {
			System.out.println("choisir la zone pour déposer la carte :");
			System.out.println("1 - Zone de popularité");
			System.out.println("2 - Zone d'attaque");
			while (!scanner.hasNextInt()) {
				System.out.println("Veuillez entrer un nombre valide (1 ou 2).");
				scanner.next();
			}
			choix = scanner.nextInt();
		} while (choix != 1 && choix != 2);
		return choix;
	}

	private void afficherZones() {
		System.out.println("\n Zones de Popularité et Attaque ");
		System.out.println(nomJoueur1 + " - Popularité : " + zonePopularite1);
		System.out.println(nomJoueur1 + " - Attaque : " + zoneAttaque1);
		System.out.println(nomJoueur2 + " - Popularité : " + zonePopularite2);
		System.out.println(nomJoueur2 + " - Attaque : " + zoneAttaque2);
	}
}
