package com.github.sym.UnoBasicGame;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame {
    private ArrayList<Player> players;
    private ArrayList<Game> gamesPlayed;
    private Scanner sc;

    //receive the main scanner
    public UnoGame(Scanner sc) {
        this.players = new ArrayList<>();
        this.gamesPlayed = new ArrayList<>();
        this.sc = sc;
    }

    //Print on console for a new game
    public void startGame() {
        System.out.println("\n=== INICIAR UNA NUEVA PARTIDA! ===");

        if (players.size() < 2){
            System.out.println("No se puede iniciar una partida se necesitan registrar almenos 2 jugadores");
            return;
        }

        System.out.print("Cuantos jugadores van a jugar? ");
        int numPlayers = sc.nextInt();
        sc.nextLine();

        if (numPlayers < 2) {
            System.out.println("No se puede iniciar una partida, se necesitan minimo 2 jugadores");
            return;
        }

        if (numPlayers > players.size()) {
            System.out.println("No hay suficientes jugadores registrados para esa cantidad.");
            return;
        }

        //Show registered players
        System.out.println("Jugadores Disponibles:");
        for (int i = 0; i < players.size();i++){
            System.out.println((i+1)+". " + players.get(i).getName());
        }

        //create actual palyers ArrayList
        ArrayList<Player> gamePlayers = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Seleccione el numero del Jugador " + (i + 1) + ": ");
            int pIndex = sc.nextInt() - 1;
            sc.nextLine();

            if (pIndex < 0 || pIndex >= players.size()) {
                System.out.println("Por favor, selecciona el numero de un jugador valido. Intenta de nuevo...");
                i--;
                continue;
            }

            Player selectedPlayer = players.get(pIndex);

            // if the player is already selected
            if (gamePlayers.contains(selectedPlayer)) {
                System.out.println("El jugador no puede efrentarse a si mismo, selecciona a dos personas distintas para una partida.");
                i--;
                continue;
            }

            gamePlayers.add(selectedPlayer);
        }

        //Create the game
        Game newGame = new Game(sc, gamePlayers);
        gamesPlayed.add(newGame);

        System.out.println("\n¡Partida creada exitosamente!");
        System.out.println("Creando el tablero...");
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