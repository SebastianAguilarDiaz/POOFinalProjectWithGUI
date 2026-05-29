package com.github.sym.controllers;



import com.github.sym.AppState;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ActualGameController {


    @FXML
    public Label errorLabel;
    
    @FXML
    public Label textOnScreen;

    
    @FXML
    public Spinner<Integer> numSpinner;


    @FXML
    public void throwFunction(){
        if(AppState.getInstance().getGamesPlayed().getLast().lastTurn.playerWon || AppState.getInstance().getGamesPlayed().getLast().getDraw()){
            return;

        }
        if(AppState.getInstance().getGamesPlayed().getLast().lastTurn.throwCard())
            AppState.getInstance().getGamesPlayed().getLast().play();
        if(AppState.getInstance().getGamesPlayed().getLast().lastTurn.playerWon){
            AppState.getInstance().addReturnButton();

        }
        
    }

    @FXML
    public void showDeckFunction(){
        AppState.getInstance().showDeck();
    }

}
