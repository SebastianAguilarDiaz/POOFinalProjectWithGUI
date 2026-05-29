package com.github.sym.unobasicgame;

import java.util.ArrayList;
import java.util.Scanner;

import com.github.sym.AppState;

public class UnoGame {
    private ArrayList<Player> players;
    private ArrayList<Game> gamesPlayed;
    private Scanner sc;

    //receive the main scanner
    public UnoGame(Scanner sc, ArrayList<Player> players, ArrayList<Game> games) {
        this.players = players;
        this.gamesPlayed = games;
        this.sc = sc;
    }

    //Print on console for a new game
    public void startGame(ArrayList<Player> playersList) {
        

        ArrayList<Player> gamePlayers = playersList;

        //Create the game
        Game newGame = new Game(sc, gamePlayers);
        
        
        this.gamesPlayed.add(newGame);
        

        
        newGame.play();
    }

    public void displayStatisticsPerPlayer() {
        System.out.println("\n=== ESTADISTICAS GENERALES DE LOS JUGADORES ===");
        if (players.isEmpty()) {
            System.out.println("No hay ningun jugador registrado todavia :(");
            return;
        }

        
        ArrayList<Showable> visualElements = new ArrayList<Showable>();
        
        for (Player p : players) {
            visualElements.add(p.getStatistics()); // Statistics implementa Showable
        }

        for (Showable element : visualElements) {
            element.show();
        }
    }

    public Game getLastGame(){
        return this.gamesPlayed.getLast();
    }

    // we show the actual players deck
    public void showDeck(){
        this.gamesPlayed.getLast().showDeck();
    }
    public void displayStatisticsPerGame() {
        System.out.println("\n=== HISTORIAL DE PARTIDAS ===");
        if (gamesPlayed.isEmpty()) {
            System.out.println("No se han jugado partidas aun.");
            return;
        }

        for (int i = 0; i < gamesPlayed.size(); i++) {
            System.out.print("Partida #" + (i + 1) + " - ");
            Game game = gamesPlayed.get(i);

            if (game.winner != null) {
                gamesPlayed.get(i).getStatistics().show();
            } else {
                System.out.println("Empate");
            }
        }
    }

    public void addPlayer() {
        System.out.println("\n=== REGISTRAR NUEVO JUGADOR ===");
        System.out.print("Ingrese el nombre del jugador: ");
        String name = sc.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacio.");
            return;
        }
        Player newPlayer = new Player(name);

        if (players.contains(newPlayer)) {
            System.out.println("Error: Ya existe un jugador registrado con el nombre '" + name + "'.");
        } else {
            players.add(newPlayer);
            System.out.println("Jugador '" + name + "' registrado con exito!");
        }

    }
}