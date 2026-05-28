package com.github.sym.controllers;

import java.io.IOException;

import com.github.sym.App;
import com.github.sym.AppState;

import javafx.fxml.FXML;

public class SelectPlayersController {


    @FXML
    public void startFunction(){
        
        AppState.getInstance().startGame();
    }
    @FXML    
    public void returnFunction() throws IOException {
        AppState.getInstance().clearPlayersInGame();
        App.setRoot("views/Menu");
    }
}
