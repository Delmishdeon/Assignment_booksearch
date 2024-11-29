package com.booksearch.booksearchapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book Search App");
        // Add your JavaFX scene setup here
        primaryStage.show();
    }
}
