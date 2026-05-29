package com.github.sym;

import java.io.IOException;

import java.util.ArrayList;

import com.github.sym.unobasicgame.Game;
import com.github.sym.unobasicgame.Player;
import com.github.sym.unobasicgame.UnoGame;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AppState {

    private static AppState instance;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Game> gamesPlayed = new ArrayList<>();
    private ArrayList<Player> actualGamePlayers = new ArrayList<>();

    private UnoGame manager=new UnoGame(null, players, gamesPlayed);
    public Label textOnScreen;
    public TextField input;
    public Label errorText;
    public Spinner<Integer> numSpinner;


    private AppState() {}

    public static AppState getInstance() {
        if (instance == null) instance = new AppState();
        return instance;
    }

    public ArrayList<Player> getPlayers() { return players; }
    public ArrayList<Game> getGamesPlayed() { return gamesPlayed; }

    public void updateSpinner(){
        var factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,this.getNumOfCardsOfTheDeck(),1);
        this.numSpinner.setValueFactory(factory);
    
    }

    public void showDeck(){
        this.manager.showDeck();
    }
    
    public int getNumOfCardsOfTheDeck(){
        return manager.getLastGame().getNumOfCardsOfTheDeck();
    }
    
    public void printTextOnScreen( char c){
        textOnScreen.setText(textOnScreen.getText()+c);
    }
    
    public void printTextOnScreen(String text){
        textOnScreen.setText(textOnScreen.getText()+text);
    }
    
    public void printErrorOnScreen(String text){
        errorText.setText(text);
    }

    public void printLineOnScreen(String text){
        textOnScreen.setText(textOnScreen.getText()+text+"\n");
    }
    
    public void printLineOnScreen() {
        printLineOnScreen("");
    }

    public void clearTextOnScreen(){
        textOnScreen.setText("");
    }

    public void startGame(){
        
        this.manager.startGame(actualGamePlayers);
    }

    public void addPlayerToGame(Player p){
        this.actualGamePlayers.add(p);
    }
    
    public int getActualGameNumOfPlayers(){return this.actualGamePlayers.size();}


    public void addReturnButton(){
        Button returnButton=new Button("Regresar");
        returnButton.setOnAction(
                e ->{
                    var root = ((VBox)(App.getScene().getRoot())).getChildren();
                    root.remove(root.getLast());
                    try{
                        App.setRoot("views/Menu");

                    }catch (Exception exception){

                    }
                }
            );
        ((VBox)App.getScene().getRoot()).getChildren().add(returnButton);
    }

    public void clearPlayersInGame(){
        this.actualGamePlayers.clear();
    }
    
    public void deletePlayerInGame(Player p){
        this.actualGamePlayers.remove(p);
    }

    public boolean addPlayer(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        Player newPlayer = new Player(name.trim());

        if (players.contains(newPlayer)) return false;
        players.add(newPlayer);
        return true;
        
    }
}