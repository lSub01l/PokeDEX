package com.example;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class OpenPokedex {

    private Pane layout;
    private Scene appScene;

    public OpenPokedex(Scene appScene) {
        this.appScene = appScene;
 
        layout = new Pane();

        // Buttons
        Button button1 = new Button("Pokedex");
        button1.setOnAction(e -> switchToPokedexScene());
        button1.setLayoutX(290);
        button1.setLayoutY(100);
        button1.setPrefWidth(100);
        button1.setPrefHeight(40);

        Button button2 = new Button("Search options");
        //button2.setOnAction(e -> Platform.exit());
        button2.setLayoutX(290);
        button2.setLayoutY(button1.getLayoutY() + 50);
        button2.setPrefWidth(100);
        button2.setPrefHeight(40);

        Button button3 = new Button("Go back");
        button3.setOnAction(e -> switchToAppScene());
        button3.setLayoutX(290);
        button3.setLayoutY(button2.getLayoutY() + 50);
        button3.setPrefWidth(100);
        button3.setPrefHeight(40);

        // Set color and add buttons
        layout.setStyle("-fx-background-color: linear-gradient(to top right, green, darkgreen);");
        layout.getChildren().addAll(button1, button2, button3);
    }

    public Pane getLayout() {
        return layout;
    }

    //Scene switching methods
    private void switchToPokedexScene() {
        Platform.runLater(() -> {
            if (appScene != null) {
                Pokedex pokedex = new Pokedex(appScene);
                Scene pokedexScene = new Scene(pokedex.getLayout(), 680, 480);
                Stage stage = (Stage) layout.getScene().getWindow();
                stage.setScene(pokedexScene);
            }
        });
    }

    private void switchToAppScene() {
        Platform.runLater(() -> {
            if (appScene != null) {
                Stage stage = (Stage) layout.getScene().getWindow();
                stage.setScene(appScene);
            }
        });
    }
}