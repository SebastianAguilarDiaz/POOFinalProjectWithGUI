package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;
import com.github.sym.AppState;
import com.github.sym.unobasicgame.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatisticsPerPlayerController {

    @FXML VBox statsBox;


    @FXML
    public void initialize() {
        for (Player p : AppState.getInstance().getPlayers()) {
            statsBox.getChildren().add(new Label(p.getStatistics().toString()));
        }

        if (AppState.getInstance().getPlayers().isEmpty()) {
            statsBox.getChildren().add(new Label("No hay jugadores registrados."));
        }

    }

    
    @FXML
    public void returnFunction() throws IOException {
        App.setRoot("views/Menu");
    }


}