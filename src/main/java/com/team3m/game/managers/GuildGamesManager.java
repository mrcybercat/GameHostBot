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


    public void removeByOwner(List<Lobby> lobbies, String ownerID){
        lobbies.remove(findByOwner(lobbies, ownerID));
    }

    public Lobby findByOwner(List<Lobby> lobbies, String ownerID){
        for(Lobby lobby : lobbies){
            if(lobby.getOwnerID().equals(ownerID)){
                return lobby;
            }
        }
        return null;
    }

    public Integer getdLobbyIndexByUserID(List<Lobby> lobbies, String userID){
        for (int i = 0; i < lobbies.size(); i++) {
            if(lobbies.get(i).getPlayerIDs().contains(userID)){
                return i;
            }
        }
        return null;
    }

}
