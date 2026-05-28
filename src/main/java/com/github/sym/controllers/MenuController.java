package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;
import com.github.sym.AppState;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

public class MenuController {



    @FXML
    public void startGameFunction() throws IOException {
        App.setRoot("views/SelectPlayers");
        VBox v =(VBox)((VBox)(App.getScene().getRoot())).getChildren().get(1);
        
        for(var n: AppState.getInstance().getPlayers()){
            
            RadioButton r= new RadioButton(n.getName());
            r.setOnAction(
                e->{
                    if(r.isSelected()){
                        AppState.getInstance().addPlayerToGame(n);
                    }
                    else
                        AppState.getInstance().deletePlayerInGame(n);
                }
            );



            v.getChildren().add(r);
        }
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