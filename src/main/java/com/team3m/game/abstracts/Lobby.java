package com.team3m.game.abstracts;

import java.util.ArrayList;
import java.util.List;

public class Lobby {


  //  private Integer lobbyID;
    private List<String> playerIDs = new ArrayList<>();
    private String lobbyName;
    private String ownerID;
    private Settings settings = null;

    public Lobby(String ownerID, String lobbyName) {
        this.ownerID = ownerID;
        this.playerIDs = new ArrayList<>();
           this.playerIDs.add(this.ownerID);
        this.lobbyName = lobbyName;
        this.settings = new Settings();
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

    public List<String> getPlayerIDs() {
        return playerIDs;
    }

    public void setPlayerIDs(List<String> playerIDs) {
        this.playerIDs = playerIDs;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String owner) {
        this.ownerID = owner;
    }
}
/*
*     public Integer getLobbyID() {
        return lobbyID;
    }

    public void setLobbyID(Integer lobbyID) {
        this.lobbyID = lobbyID;
    }
* */