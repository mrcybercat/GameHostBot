package com.team3m.game.abstracts;

import com.team3m.game.coup.CoupGame;
import com.team3m.game.mafia.MafiaGame;

public class Settings {

    private Game game = new DefaultGame();
    private Integer maxPlayers = 0;

    public Settings(){};

    public Settings(Game game, Integer maxPlayers) {
        this.game = game;
        this.maxPlayers = maxPlayers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setGameByName(String name){
        switch (name){
            case "mafia":
                this.game = new MafiaGame();
                return;
            case "coup":
                this.game = new CoupGame();
                return;
            default:
                this.game = new DefaultGame();
                return;
        }
    }
}
