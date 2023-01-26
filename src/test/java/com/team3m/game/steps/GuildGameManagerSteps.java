package com.team3m.game.steps;

import com.team3m.game.abstracts.Lobby;
import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.thucydides.core.annotations.Step;

public class GuildGameManagerSteps {

    GamesManager gamesManager = GamesManager.getInstance();
    GuildGamesManager guildManager = null;

    @Step("Adding guild manager to game manager")
    public void addGuildManger(String guildId) {
        gamesManager.getGuildManagers().add(new GuildGamesManager(guildId));
    }

    @Step("Searching guild manager by id")
    public GuildGamesManager findGuildManager(String guildId) {
        this.guildManager = gamesManager.getGuildManager(guildId);
        return guildManager;
    }

    @Step("Adding lobby to guild manager")
    public void addLobby(String ownerId, String lobbyName) {
        guildManager.getLobbies().add(new Lobby(ownerId, lobbyName));
    }

    @Step("Adding user lobby")
    public void addUser(String ownerId, String userId) {
        guildManager.findByOwner(guildManager.getLobbies(), ownerId).getPlayerIDs().add(userId);
    }


    @Step("Searching lobby by owner id")
    public Lobby getLobbyByOwner(String ownerId) {
        return guildManager.findByOwner(guildManager.getLobbies(), ownerId);
    }

    @Step("Searching  lobby by user id")
    public Lobby getLobbyByUser(String ownerId) {
        Integer lobbyIndex = guildManager.getLobbyIndexByUserID(guildManager.getLobbies(), ownerId);
        if(lobbyIndex == null){
            return null;
        }
        return guildManager.getLobbies().get(lobbyIndex);
    }

    @Step("Removing lobby by owner id")
    public boolean removeLobby(String ownerId) {
        return guildManager.removeByOwner(guildManager.getLobbies(), ownerId);
    }

    @Step("Checking for owner duplicates")
    public boolean ownerDuplicationCheck(String ownerId) {
        return guildManager.ownerDuplicationCheck(guildManager.getLobbies(), ownerId);
    }


    @Step("Removing all lobbies")
    public void removeAllLobies() {
        guildManager.getLobbies().clear();
    }
}
