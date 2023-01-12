package com.team3m.game.abstracts;

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
}
