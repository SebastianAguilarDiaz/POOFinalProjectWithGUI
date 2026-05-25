package com.github.sym.UnoBasicGame;

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
    }

    public boolean throwCard() {
        this.b.printBoard();

        ArrayList<Card> hand = this.b.getPlayersDeck();
        boolean validPlay = false;

        while (!validPlay) {
            System.out.println("\nSelecciona el numero de carta a tirar (1 a " + hand.size() + ") o 0 para robar una carta:");
            int choice = this.sc.nextInt();
            this.sc.nextLine();

            //draw a card
            if (choice == 0) {
                System.out.println("Robaste una carta!");
                hand.add(Card.randomCard(this.b));
                validPlay = true;


            //play a card
            } else if (choice > 0 && choice <= hand.size()) {
                Card selected = hand.get(choice - 1);
                Card last = this.b.getLastCard();

                if (selected.compatible(last)) {
                    System.out.println("Carta jugada!");

                    selected.throwIt();

                    this.b.setLastCard(selected);
                    hand.remove(choice - 1);

                    validPlay = true;
                    if (hand.isEmpty()) {
                        this.playerWon = true;
                    }
                } else {
                    System.out.println("Movimiento invalido! La carta debe coincidir en color, numero o ser del mismo tipo especial.");
                }
            } else {
                //invalid option
                System.out.println("Opcion no valida.");
            }
        }
        this.nextPlayer =(this.b.getActualPlayerIndex() + b.getFlow() + this.b.getNumberOfPlayers()) % this.b.getNumberOfPlayers();

        return true;
    }
}