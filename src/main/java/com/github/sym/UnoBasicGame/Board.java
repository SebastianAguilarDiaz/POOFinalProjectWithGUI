package com.github.sym.unobasicgame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Board {
    private ArrayList<Player> players;
    
    public static final int CARDS_PER_PLAYER = 7;
    private int flow = 1;
    private int actualPlayerIndex;
    private Scanner sc;
    private ArrayList<ArrayList<Card>> playersDecks=new ArrayList<ArrayList<Card>>();
    private Card lastCard;

    public Board(Scanner sc, ArrayList<Player> players) {
        this.actualPlayerIndex=0;
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
        return playersDecks.get(actualPlayerIndex);
    }

    public void printBoard() {
        this.clearScreen();
        System.out.println("Ultima carta");
        this.lastCard.show();

        // when the player is ready to play 
        System.out.println("Presione enter para ver el mazo del jugador "+ this.players.get(this.getActualPlayerIndex()).getName());
        sc.nextLine();

        // first iterates on height
        for(int j=0;j<Card.HEIGHT;j++){

            // then iterates on cards
            for(int i=0;i<this.playersDecks.get(this.getActualPlayerIndex()).size();i++){
                
                // finally iterates on the width of each card
                for(int k=0;k<Card.WIDTH;k++){
                    System.out.print(this.playersDecks.get(this.getActualPlayerIndex()).get(i).getChar(k, j));
                }
                System.out.print(' ');
                
            }
            System.out.println();
        }
        System.out.println();

    }

    // so the next player doesnt see the last players deck
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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