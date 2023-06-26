package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    Stage window;
    Scene scene1, openPokedexScene;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Layout settings
        window = primaryStage;
        
        // Button 1
        Button button1 = new Button("Open pokedex");
        button1.setOnAction(e -> window.setScene(openPokedexScene));
        button1.setLayoutX(290);
        button1.setLayoutY(150);
        button1.setPrefWidth(100);
        button1.setPrefHeight(40);

        // Button 2
        Button button2 = new Button("Exit");
        button2.setOnAction(e -> Platform.exit());
        button2.setLayoutX(290);
        button2.setLayoutY(220);
        button2.setPrefWidth(100);
        button2.setPrefHeight(40);

        // App layout
        Pane layout1 = new Pane();
        layout1.getChildren().addAll(button1, button2);
        scene1 = new Scene(layout1, 680, 480);
        layout1.setStyle("-fx-background-color: linear-gradient(to top right, green, darkgreen);");

        // Create an instance of OpenPokedex and assign it to openPokedexScene
        OpenPokedex openPokedex = new OpenPokedex(scene1);
        openPokedexScene = new Scene(openPokedex.getLayout(), 680, 480);

        // Set initial scene
        window.setScene(scene1);
        window.setTitle("PokeDex by Sub01");
        window.show();
    }
}