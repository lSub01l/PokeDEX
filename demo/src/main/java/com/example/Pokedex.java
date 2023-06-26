package com.example;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Pokedex {

    private Pane layout;
    private Scene appScene;
    private List<Pokemon> pokemonList;
    private int currentIndex;
    private ImageView pokemonImage;
    private Label descriptionLabel;

    public Pokedex(Scene appScene) {
        this.appScene = appScene;
        this.pokemonList = new ArrayList<>();
        this.currentIndex = 0;

        layout = new Pane();

        // Load Pokémon data from file
        loadPokemonDataFromFile("pokemons.txt");

        // Label element for displaying the description
        descriptionLabel = new Label("");
        descriptionLabel.setFont(Font.font("Arial", 14));
        descriptionLabel.setLayoutX(40);
        descriptionLabel.setLayoutY(80);
        descriptionLabel.setPrefHeight(200);
        descriptionLabel.setPrefWidth(275);
        descriptionLabel.setStyle("-fx-background-color: linear-gradient(to top, darkgrey, grey); -fx-background-radius: 10;");
        descriptionLabel.setTextFill(Color.BLACK);
        descriptionLabel.setWrapText(true);

        // Create and configure the ImageView for the Pokemon image
        pokemonImage = new ImageView();
        pokemonImage.setFitWidth(260);
        pokemonImage.setFitHeight(260);
        pokemonImage.setLayoutX(380);
        pokemonImage.setLayoutY(40);

        // Load and set the image for the ImageView
        updatePokemonImage();

        // Buttons
        Button button1 = new Button("GO BACK");
        button1.setOnAction(e -> switchToOpenPokedexScene());
        button1.setLayoutX(640 / 10);
        button1.setLayoutY((480 / 10) * 8);
        button1.setPrefWidth(140);
        button1.setPrefHeight(40);

        Button button2 = new Button("PREVIOUS POKEMON");
        button2.setOnAction(e -> switchToPreviousPokemon());
        button2.setLayoutX(button1.getLayoutX() + 196);
        button2.setLayoutY((480 / 10) * 8);
        button2.setPrefWidth(140);
        button2.setPrefHeight(40);

        Button button3 = new Button("NEXT POKEMON");
        button3.setOnAction(e -> switchToNextPokemon());
        button3.setLayoutX(button2.getLayoutX() + 196);
        button3.setLayoutY((480 / 10) * 8);
        button3.setPrefWidth(140);
        button3.setPrefHeight(40);

        // Set color for the layout background
        layout.setStyle("-fx-background-color: linear-gradient(to top right, green, darkgreen);");

        // Add the UI elements to the layout
        layout.getChildren().addAll(pokemonImage, descriptionLabel, button1, button2, button3);
    }

    // Functions for handling buttons
    private void switchToOpenPokedexScene() {
        Platform.runLater(() -> {
            if (appScene != null) {
                OpenPokedex openPokedex = new OpenPokedex(appScene);
                Scene openPokedexScene = new Scene(openPokedex.getLayout(), 680, 480);
                Stage stage = (Stage) layout.getScene().getWindow();
                stage.setScene(openPokedexScene);
            }
        });
    }

    private void switchToPreviousPokemon() {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = pokemonList.size() - 1;
        }
        updatePokemonImage(); 
    }

    private void switchToNextPokemon() {
        currentIndex++;
        if (currentIndex >= pokemonList.size()) {
            currentIndex = 0;
        }
        updatePokemonImage(); 
    }

    //Functions to update Pokemon data
    private void updatePokemonImage() { 
        Pokemon pokemon = pokemonList.get(currentIndex);

        // Load and set the image for the ImageView
        String imagePath = pokemon.getImagePath();
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        pokemonImage.setImage(image);
    
        // Construct the description String
        String description = "Name: " + pokemon.getPokemonName() + "\n"
        + "Type: " + pokemon.getPokemonType() + "\n"
        + "Primary Element: " + pokemon.getPokemonElementP() + "\n"
        + "Secondary Element: " + pokemon.getPokemonElementS() + "\n"
        + "Description: " + pokemon.getDescription();
        // Set the description String as the text for the descriptionLabel
        descriptionLabel.setText(description);
    }

    private void loadPokemonDataFromFile(String filename) {
        try (InputStream inputStream = getClass().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    int index = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String type = parts[2];
                    String elementP = parts[3];
                    String elementS = parts[4];
                    String description = parts[5];
                    String imagePath = parts[6];

                    Pokemon pokemon = new Pokemon(index, name, type, elementP, elementS, description, imagePath);
                    pokemonList.add(pokemon);
                } else {
                    System.out.println("Invalid line: " + line); // Debug output
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Pane getLayout() {
        return layout;
    }
}