package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;
import com.github.sym.AppState;
import com.github.sym.unobasicgame.Game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatisticsPerGameController {

    @FXML VBox statsBox;


    @FXML
    public void initialize() {
        int i = 1;
        for (Game g : AppState.getInstance().getGamesPlayed()) {
            statsBox.getChildren().add(new Label("Partida #" + i + ": " + g.getStatistics().toString()));
            i++;
        }

        if (AppState.getInstance().getGamesPlayed().isEmpty()) {
            statsBox.getChildren().add(new Label("No se han jugado partidas aún."));
        }

    }

    
    @FXML
    public void returnFunction() throws IOException {
        App.setRoot("views/Menu");
    }


}