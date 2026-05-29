package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;
import com.github.sym.AppState;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectPlayersController {


    @FXML
    public void startFunction() throws IOException{
        if(AppState.getInstance().getActualGameNumOfPlayers()>=2){
            App.setRoot("views/ActualGame");
            
            AppState.getInstance().textOnScreen     =(Label)    (((VBox)(App.getScene().getRoot())).getChildren().get(1));
            
            AppState.getInstance().errorText        =(Label)    (((VBox)(App.getScene().getRoot())).getChildren().get(2));
            AppState.getInstance().numSpinner       =((Spinner<Integer>)(((HBox)(((VBox)App.getScene().getRoot()).getChildren().get(4))).getChildren().get(0)));


            AppState.getInstance().startGame();
            
            
        }     
    }
    
    @FXML    
    public void returnFunction() throws IOException {
        AppState.getInstance().clearPlayersInGame();
        App.setRoot("views/Menu");
    }
}
