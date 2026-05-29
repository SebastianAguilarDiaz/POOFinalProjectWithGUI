package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;
import com.github.sym.AppState;
import com.github.sym.unobasicgame.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddPlayerController {

    @FXML TextField textField;
    @FXML VBox playerListBox;
    @FXML Label errorLabel;


    @FXML
    public void initialize() {
        refreshList();
    }

    
    public void addPlayerFunction() {
        
        String name = textField.getText();
        boolean added = AppState.getInstance().addPlayer(name);

        if (added) {
            textField.clear();
            errorLabel.setText("");
            refreshList();
        } else {
            errorLabel.setText("Nombre inválido o ya existe.");
        }

    }

    private void refreshList() {
        playerListBox.getChildren().clear();

        for (Player p : AppState.getInstance().getPlayers()) {
            playerListBox.getChildren().add(new Label("• " + p.getName()));
        }
    }

    public void returnFunction() throws IOException {
        App.setRoot("views/Menu");
    }


}