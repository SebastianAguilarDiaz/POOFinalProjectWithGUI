package com.github.sym.unobasicgame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.github.sym.AppState;

public class Board {
    private ArrayList<Player> players;
    
    public static final int CARDS_PER_PLAYER = 7;
    private int flow = 1;
    private int actualPlayerIndex=0;
    private Scanner sc;
    private ArrayList<ArrayList<Card>> playersDecks=new ArrayList<ArrayList<Card>>();
    private Card lastCard;
    private boolean deckOnScreen=false;

    public Board(Scanner sc, ArrayList<Player> players) {
        
        this.players=players;
        this.sc = sc;
        
        this.makeDecks();
        this.setLastCard();
        

    }
    
    
    public void makeDecks() {
        for(int i=0;  i<players.size();i++){
            ArrayList<Card> actualDeck =new ArrayList<Card>(); 
            for(int j=0;j<Board.CARDS_PER_PLAYER;j++){
                Card actualCard=Card.randomCard(this);
                actualDeck.add(actualCard);

            
            }
            this.playersDecks.add(actualDeck);
        }
    }

    public void changeFlow() {
        this.flow*=-1;
    }
    
    public int getFlow() {
        return this.flow;
    }


    public void addCardsToAPlayer(int numOfCards) {
        // circular index
        int playerToAddIndex=(((this.getActualPlayerIndex()+this.getFlow())%this.getNumberOfPlayers())+this.getNumberOfPlayers())%this.getNumberOfPlayers();
        
        for(int i=0;i<numOfCards;i++){
            Card actualCard=Card.randomCard(this);
            this.playersDecks.get(playerToAddIndex).add(actualCard);


        }
        

    }

    public int getActualPlayerIndex() {
        return this.actualPlayerIndex;
    }

    public void setActualPlayerIndex(int index) {
        this.actualPlayerIndex=index;
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }

    public ArrayList<Card> getPlayersDeck() {
        return playersDecks.get(this.getActualPlayerIndex());
    }

    public int getPlayerDeckSize(int index) {
        return this.playersDecks.get(index).size();
    }

    public void printBoard() {
        this.clearScreen();
        AppState.getInstance().printLineOnScreen("Ultima carta");
        this.lastCard.show();
        AppState.getInstance().printLineOnScreen();
        AppState.getInstance().printLineOnScreen("Turno de "+this.players.get(this.getActualPlayerIndex()).getName());
        

    }

    public void showDeck(){
        if(this.deckOnScreen) return;
        // first iterates on height
        for(int j=0;j<Card.HEIGHT;j++){

            // then iterates on cards
            for(int i=0;i<this.playersDecks.get(this.getActualPlayerIndex()).size();i++){
                
                // finally iterates on the width of each card
                for(int k=0;k<Card.WIDTH;k++){
                    AppState.getInstance().printTextOnScreen(this.playersDecks.get(this.getActualPlayerIndex()).get(i).getChar(k, j));
                }
                AppState.getInstance().printTextOnScreen(' ');
                
                
            }
            AppState.getInstance().printLineOnScreen();
        }
        AppState.getInstance().printLineOnScreen();
        this.deckOnScreen= true;

    }

    // so the next player doesnt see the last players deck
    public void clearScreen() {
        this.deckOnScreen=false;
        AppState.getInstance().clearTextOnScreen();
    }

    public Card getLastCard() {
        return this.lastCard;
    }

    public void setLastCard(Card c) {
        this.lastCard = c;
    }

    public void setLastCard(){
        this.lastCard=Card.randomCard(this);
    }
}