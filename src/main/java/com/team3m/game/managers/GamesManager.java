package com.team3m.game.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GamesManager {
    private static GamesManager INSTANCE;
    private List<GuildGamesManager> guildManagers = new ArrayList<>();

    public GamesManager() {
        this.guildManagers = new ArrayList<>();
    }

    public List<GuildGamesManager> getGuildManagers() {
        return guildManagers;
    }

    public void setGuildManagers(List<GuildGamesManager> guildManagers) {
        this.guildManagers = guildManagers;
    }

    public GuildGamesManager getGuildManager(String guildID) {
        for (GuildGamesManager guildManager: this.guildManagers) {
            if(Objects.equals(guildManager.getGuildID(), guildID)){
                return guildManager;
            }
        }
        return null;
    }


    public static GamesManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GamesManager();
        }
        return INSTANCE;
    }
}
