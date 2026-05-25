package com.github.sym.unobasicgame;

import java.util.Scanner;

public class Player {
    private String name;
    private Statistics statisticsOfThePlayer;

    public Player(String name) {
        this.setName(name);
        this.statisticsOfThePlayer = new Statistics();
    }

    public Player(Scanner sc) {
        System.out.println("Ingresa el nombre del jugador");
        
        this(sc.nextLine());
        

    }

    //getter the name
    public String getName() {
        return this.name;
    }
    //getter the statistics
    public Statistics getStatistics() {
        return this.statisticsOfThePlayer;
    }
    //Setter the name
    private void setName(String name) {
        this.name = name;
    }
    //Setter the statistics
    private void setStatistics(Statistics statistics) {
        this.statisticsOfThePlayer = statistics;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass()!= o.getClass()) return false;
        Player player = (Player) o;
        return name.equalsIgnoreCase(player.name);
    }
    //Inner class
    public class Statistics implements Updatable, Showable {
        public int gamesWon = 0;
        public int gamesLost = 0;

        @Override //aqui manden el resultado de la partida jajaja
        public void update(Updatable x) {
            if (x instanceof Game.Statistics) {
                Game.Statistics result = (Game.Statistics) x;
                if (result.isWinner(Player.this)) {
                    this.gamesWon++;
                } else {
                    this.gamesLost++;
                }
            }
        }

        @Override //Esta cosa es para la consola
        public void show() {
            System.out.println(this.toString());
        }
        //Esta cosa sirve para el javafx xD
        @Override
        public String toString() {
            return "Jugador " + name + " | Partidas Ganadas: " + gamesWon
                    + " |Partidas Perdidas: " + gamesLost;
        }
    }
}