package com.github.sym.controllers;



import com.github.sym.AppState;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;


public class ActualGameController {


    @FXML
    public Label errorLabel;
    
    @FXML
    public Label textOnScreen;

    
    @FXML
    public Spinner<Integer> numSpinner;


    @FXML
    public void throwFunction(){

        // if someone won or the game result was a draw the button wont do anything
        if(AppState.getInstance().getGamesPlayed().getLast().lastTurn.playerWon || AppState.getInstance().getGamesPlayed().getLast().getDraw()){
            return;

        }

        // if the turn succeed we have to create a new turn
        if(AppState.getInstance().getGamesPlayed().getLast().lastTurn.throwCard())
            AppState.getInstance().getGamesPlayed().getLast().play();
        
        // if someone won or the game result was a draw we must add the return button
        if(AppState.getInstance().getGamesPlayed().getLast().lastTurn.playerWon || AppState.getInstance().getGamesPlayed().getLast().getDraw()){
            AppState.getInstance().addReturnButton();

        }
        
    }

    @FXML
    public void showDeckFunction(){
        AppState.getInstance().showDeck();
    }

}
