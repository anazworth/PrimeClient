package com.anazworth.primeclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class PrimeClientApplication extends Application {

    public static TextArea guiOutput = new TextArea();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Prime Number Client");
        Label inputLabel = new Label("Enter a number: ");
        TextField inputField = new TextField();
        Button submitButton = new Button("Submit");
        guiOutput.setEditable(false);
        guiOutput.setWrapText(true);
        guiOutput.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        inputLabel.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        inputField.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        submitButton.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        submitButton.setOnAction(e -> {
            try {
                PrimeHandler.getPrime(inputField.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox root = new VBox();
        HBox inputBox = new HBox();
        inputBox.getChildren().addAll(inputLabel, inputField, submitButton);
        HBox outputBox = new HBox();
        outputBox.getChildren().add(guiOutput);

        outputBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        inputBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        root.autosize();
        root.getChildren().add(inputBox);
        root.getChildren().add(outputBox);

        Scene scene = new Scene(root, 640, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void log(String message) {
        guiOutput.appendText(message + "\n");
    }

    public static void main(String[] args) throws IOException {
        launch();

    }
}