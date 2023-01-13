package com.team3m.game.abstracts;

import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lobby {


  //  private Integer lobbyID;
    private String lobbyName;
    private String ownerID;
    private Set<String> playerIDs = new HashSet<>();
    private Settings settings = null;
    private Boolean isGameStarted = false;

    public Lobby(String ownerID, String lobbyName) {
        this.ownerID = ownerID;
        this.playerIDs = new HashSet<>();
        this.playerIDs.add(this.ownerID);
        this.lobbyName = lobbyName;
        this.settings = new Settings();
    }

    public Lobby(Lobby lobby){
        this.lobbyName = lobby.getLobbyName();
        this.ownerID = lobby.getOwnerID();
        this.playerIDs = lobby.getPlayerIDs();
        this.settings = lobby.getSettings();
        this.isGameStarted = lobby.getGameStarted();
    }

    public String getLobbyName() {
        return lobbyName;
    }
    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }
    public Settings getSettings() {
        return settings;
    }
    public void setSettings(Settings settings) {
        this.settings = settings;
    }
    public Set<String> getPlayerIDs() {
        return playerIDs;
    }
    public void setPlayerIDs(Set<String> playerIDs) {
        this.playerIDs = playerIDs;
    }
    public String getOwnerID() {
        return ownerID;
    }
    public void setOwnerID(String owner) {
        this.ownerID = owner;
    }
    public Boolean getGameStarted() {
        return isGameStarted;
    }
    public void setGameStarted(Boolean gameStarted) {
        this.isGameStarted = gameStarted;
    }
}
