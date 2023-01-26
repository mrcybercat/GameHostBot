package com.team3m.game.steps;

import com.team3m.game.managers.GamesManager;
import com.team3m.game.managers.GuildGamesManager;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GameManagerSteps {
    GamesManager gamesManager = GamesManager.getInstance();

    @Step("Adding guild manager to game manager")
    public void addGuildManger(String guildId) {
        gamesManager.getGuildManagers().add(new GuildGamesManager(guildId));
    }

    @Step("Searching guild manager by id")
    public GuildGamesManager findGuildManager(String guildId) {
        return gamesManager.getGuildManager(guildId);
    }

}
