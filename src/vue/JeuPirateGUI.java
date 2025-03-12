package vue;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

public class JeuPirateGUI extends Application {

    private Joueur joueur1 = new Joueur("Alice");
    private Joueur joueur2 = new Joueur("Bob");
    private VBox centre;
    private HBox zoneCartesJouees;
    private Label tourLabel;
    private Joueur joueurActuel;
    private HBox cartesJoueur1;
    private HBox cartesJoueur2;
    private Button startButton;

    @Override
    public void start(Stage primaryStage) {
        joueurActuel = joueur1;

        // Conteneur principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: darkred;");

        // Zone du joueur gauche (Alice)
        VBox joueurGauche = creerZoneJoueur(joueur1, "/images/pirate1.png");
        root.setLeft(joueurGauche);

        // Zone du joueur droite (Bob)
        VBox joueurDroite = creerZoneJoueur(joueur2, "/images/pirate2.png");
        root.setRight(joueurDroite);

        // Zone centrale (jeu)
        centre = new VBox(15);
        centre.setAlignment(Pos.CENTER);

        tourLabel = new Label("Cliquez sur Démarrer pour commencer");
        tourLabel.setTextFill(Color.WHITE);
        tourLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        zoneCartesJouees = new HBox(10);
        zoneCartesJouees.setAlignment(Pos.CENTER);

        startButton = new Button("Démarrer la Partie");
        startButton.setOnAction(event -> demarrerJeu());

        centre.getChildren().addAll(tourLabel, startButton, zoneCartesJouees);
        root.setCenter(centre);

        // Affichage
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Jeu des Pirates");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox creerZoneJoueur(Joueur joueur, String imagePath) {
        VBox zoneJoueur = new VBox(10);
        zoneJoueur.setAlignment(Pos.CENTER);

        ImageView avatar = chargerImage(imagePath);
        Label vieLabel = new Label("Vie: " + joueur.getVie());
        HBox cartesZone = new HBox(5);
        cartesZone.setAlignment(Pos.CENTER);

        if (joueur == joueur1) cartesJoueur1 = cartesZone;
        else cartesJoueur2 = cartesZone;

        zoneJoueur.getChildren().addAll(avatar, vieLabel, cartesZone);
        return zoneJoueur;
    }

    private ImageView chargerImage(String chemin) {
        try {
            Image image = new Image(getClass().getResource(chemin).toExternalForm());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            return imageView;
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de l'image : " + chemin);
            return new ImageView();
        }
    }

    private void demarrerJeu() {
 
        afficherMain(joueur1, cartesJoueur1);
        afficherMain(joueur2, cartesJoueur2);
        tourLabel.setText("Tour de " + joueurActuel.getNom());
        startButton.setDisable(true);
    }

    private void afficherMain(Joueur joueur, HBox zoneCartes) {
        zoneCartes.getChildren().clear();
        List<Cartes> main = joueur.getMain();
        for (Cartes carte : main) {
            Button boutonCarte = creerCarte(carte.getNomCarte(), "lightblue", joueur, zoneCartes);
            zoneCartes.getChildren().add(boutonCarte);
        }
    }

    private Button creerCarte(String texte, String couleur, Joueur joueur, HBox zoneCartes) {
        Button carte = new Button(texte);
        carte.setStyle("-fx-background-color: " + couleur + "; -fx-padding: 10px; -fx-font-weight: bold;");
        carte.setOnAction(event -> {
            ajouterCarteDansZoneJouee(texte, couleur);
            zoneCartes.getChildren().remove(carte);
            joueur.getMain().removeIf(c -> c.getNomCarte().equals(texte));
            changerTour();
        });
        return carte;
    }

    private void ajouterCarteDansZoneJouee(String texte, String couleur) {
        Button carteJouee = new Button(texte);
        carteJouee.setStyle("-fx-background-color: " + couleur + "; -fx-padding: 10px; -fx-font-weight: bold;");
        carteJouee.setDisable(true);
        zoneCartesJouees.getChildren().add(carteJouee);
    }

    private void changerTour() {
        joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        tourLabel.setText("Tour de " + joueurActuel.getNom());
        afficherMain(joueurActuel, (joueurActuel == joueur1) ? cartesJoueur1 : cartesJoueur2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
