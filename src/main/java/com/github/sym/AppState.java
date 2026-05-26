package com.github.sym;

import java.util.ArrayList;

import com.github.sym.unobasicgame.Game;
import com.github.sym.unobasicgame.Player;

public class AppState {

    private static AppState instance;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Game> gamesPlayed = new ArrayList<>();

    private AppState() {}

    public static AppState getInstance() {
        if (instance == null) instance = new AppState();
        return instance;
    }

    public ArrayList<Player> getPlayers() { return players; }
    public ArrayList<Game> getGamesPlayed() { return gamesPlayed; }

    public boolean addPlayer(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        Player newPlayer = new Player(name.trim());

        if (players.contains(newPlayer)) return false;
        players.add(newPlayer);
        return true;
        
    }
}