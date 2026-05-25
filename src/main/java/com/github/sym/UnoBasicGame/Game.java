package com.github.sym.UnoBasicGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    public Player winner;
    private Board board;
    private int maxOfTurns = 40;
    private Statistics statisticsOfTheGame;
    private Scanner sc;

    public Game(Scanner sc, ArrayList<Player> playersRegistered) {
        this.players = playersRegistered;
        this.sc = sc;
        this.statisticsOfTheGame = new Statistics();
        this.board = new Board(this.sc, playersRegistered);
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public Statistics getStatistics() {
        return this.statisticsOfTheGame;
    }

    public void updateStatistics() {
        this.statisticsOfTheGame.update(null);
        for (Player p : players) {
            p.getStatistics().update(this.statisticsOfTheGame);
        }

    }

    public void play() {
        System.out.println("\n=== COMIENZA UNA NUEVA PARTIDA ===");
        int turns = 0;
        
        this.board.setActualPlayerIndex(0);
        Player currentPlayer = this.players.get(this.board.getActualPlayerIndex());
        do{
            
            Turn currentTurn = new Turn(this.sc, currentPlayer, this.board);
            
            currentTurn.throwCard();

            if (currentTurn.playerWon) {
                this.winner = currentPlayer;
                System.out.println("\n" + this.winner.getName() + " ha ganado la partida!");
            }
            turns++;
            this.board.setActualPlayerIndex(currentTurn.nextPlayer);
            currentPlayer=this.players.get(this.board.getActualPlayerIndex());
        }while(this.winner == null && turns < this.maxOfTurns);
        
        if (this.winner == null) {
            System.out.println("\nSe alcanzo el limite de turnos (" + this.maxOfTurns + "). Es un empate.");
        }
        
        this.updateStatistics();
    }

    public class Statistics implements Updatable, Showable {
        public ArrayList<Integer> cardsPerPlayer;

        public Statistics() {
            this.cardsPerPlayer = new ArrayList<Integer>();
            for(int i=0;i<Game.this.players.size();i++){
                this.cardsPerPlayer.add((Integer)Board.CARDS_PER_PLAYER);
            }
        }

        //Check the winner
        public boolean isWinner(Player p) {
            if (Game.this.winner == null) {
                return false;
            }
            return Game.this.winner.equals(p);
        }

        @Override
        public void update(Updatable x) {
            for(int i=0;i<Game.this.players.size();i++){
                Game.this.board.setActualPlayerIndex(i);
                this.cardsPerPlayer.set(i,Game.this.board.getPlayersDeck().size());
            }
        }
        @Override
        public void show() {
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            if (Game.this.winner != null) {
                String x = "Ganador de la partida: " + Game.this.winner.getName()+"\n";
                for(int i=0;i<cardsPerPlayer.size();i++){
                    x+="\t" + Game.this.getPlayers().get(i).getName()+ " "+ cardsPerPlayer.get(i) +" cartas\n";
                }
                
                return x;
            }
            return "Partida finalizada sin un ganador definitivo.";
        }
    }
}