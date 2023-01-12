package com.team3m.game.managers;

import com.team3m.game.abstracts.Lobby;

import java.util.ArrayList;
import java.util.List;

public class GuildGamesManager {

    private String guildID;
    private List<Lobby> lobbies = new ArrayList<Lobby>();

    public GuildGamesManager(String guildID) {
        this.guildID = guildID;
        this.lobbies = new ArrayList<>();
    }

    public String getGuildID() {
        return guildID;
    }

    public void setGuildID(String guildID) {
        this.guildID = guildID;
    }

    public List<Lobby> getLobbies() {
        return lobbies;
    }

    public void setLobbies(List<Lobby> lobbies) {
        this.lobbies = lobbies;
    }
}
