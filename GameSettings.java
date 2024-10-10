package com.example.myapplication5;
import java.io.Serializable;

public class GameSettings implements Serializable {
    private final int numberOfPlayers;
    private final int skipTurns;
    private final int reloads;

    public GameSettings(int numberOfPlayers, int skipTurns, int reloads) {
        this.numberOfPlayers = numberOfPlayers;
        this.skipTurns = skipTurns;
        this.reloads = reloads;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getSkipTurns() {
        return skipTurns;
    }

    public int getReloads() {
        return reloads;
    }
}
