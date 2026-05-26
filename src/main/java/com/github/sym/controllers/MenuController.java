package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MenuController {



    @FXML
    public void startGameFunction() {
        System.out.println("Hola");
    }



    @FXML
    public void displayStatisticsPerGameFunction() throws IOException {
        App.setRoot("views/StatisticsPerGame");
    }

    @FXML
    public void displayStatisticsPerPlayerFunction() throws IOException {
        App.setRoot("views/StatisticsPerPlayer");
    }

    @FXML
    public void addPlayerFunction() throws IOException {
        App.setRoot("views/AddPlayer");
    }

    @FXML
    public void exitFunction() {
        Platform.exit();
    }

    
}