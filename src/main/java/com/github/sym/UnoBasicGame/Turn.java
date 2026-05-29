package com.github.sym.unobasicgame;

import com.github.sym.AppState;

import java.util.Scanner;
import java.util.ArrayList;

public class Turn {
    public int nextPlayer;
    public boolean playerWon = false;
    private Scanner sc;
    private Player p;
    private Board b;

    public Turn(Scanner sc, Player p, Board b) {
        this.sc = sc;
        this.p = p;
        this.b = b;
        this.b.printBoard();
        AppState.getInstance().updateSpinner();
    }

    public boolean throwCard() {
        



        AppState.getInstance().printErrorOnScreen("");
        ArrayList<Card> hand = this.b.getPlayersDeck();
        

        
        
        int choice = AppState.getInstance().numSpinner.getValue().intValue();

        //draw a card
        if (choice == 0) {
            
            hand.add(Card.randomCard(this.b));
            
            
        } 


        // valid choice
        else if (choice > 0 && choice <= hand.size()) {
            Card selected = hand.get(choice - 1);
            Card last = this.b.getLastCard();
            // correct card
            if (selected.compatible(last)) {
                

                selected.throwIt();

                this.b.setLastCard(selected);
                hand.remove(choice - 1);

                
                if (hand.isEmpty()) {
                    this.playerWon = true;
                }


            } 
            // wrong card
            else {
                AppState.getInstance().printErrorOnScreen("Movimiento invalido! La carta debe coincidir en color, numero o ser del mismo tipo especial.");
                
                // the next player is the same player
                this.nextPlayer=b.getActualPlayerIndex();
                
                

                return false;
            }
        } 
        // wrong choice
        else {
            //invalid option
            AppState.getInstance().printErrorOnScreen("Opcion no valida.");
            
            // the next player is the same player
            this.nextPlayer=b.getActualPlayerIndex();
            
            
            

            return false;
        }
        
        
        this.nextPlayer=((this.b.getActualPlayerIndex() + b.getFlow()) %this.b.getNumberOfPlayers() + this.b.getNumberOfPlayers()) % this.b.getNumberOfPlayers();
        
        this.b.setActualPlayerIndex(nextPlayer);
        

        return true; 
    }
}