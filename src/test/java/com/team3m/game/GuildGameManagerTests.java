package com.team3m.game;

import com.team3m.game.steps.GuildGameManagerSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.thucydides.core.annotations.Steps;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GuildGameManagerTests {
    @Rule
    public SpringIntegrationMethodRule springMethodIntegration = new SpringIntegrationMethodRule();

    @Steps
    GuildGameManagerSteps guildGameManagerSteps;
    String guildId = "1234567";

    String[] ownersIds = new String[] {"1", "2"};

    String[] usersIds = new String[] {"4", "5"};


    @Before
    public void setUp() {
        guildGameManagerSteps.addGuildManger(guildId);
        guildGameManagerSteps.findGuildManager(guildId);
    }

    @Test
    public void removeExistingLobby() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        Assert.assertTrue(guildGameManagerSteps.removeLobby(ownersIds[0]));
    }

    @Test
    public void removeNonExistingLobby() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        Assert.assertFalse(guildGameManagerSteps.removeLobby(ownersIds[1]));
    }

    @Test
    public void ownerDuplicationCheckTrue() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        Assert.assertTrue(guildGameManagerSteps.ownerDuplicationCheck(ownersIds[0]));
    }

    @Test
    public void ownerDuplicationCheckFalse() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        Assert.assertFalse(guildGameManagerSteps.ownerDuplicationCheck(ownersIds[1]));
    }

    @Test
    public void getLobbyByExistingOwner() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        Assert.assertNotNull(guildGameManagerSteps.getLobbyByOwner(ownersIds[0]));
    }

    @Test
    public void getLobbyByNonExistingOwner() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        Assert.assertNull(guildGameManagerSteps.getLobbyByOwner(ownersIds[1]));
    }

    @Test
    public void getLobbyByExistingUser() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        guildGameManagerSteps.addUser(ownersIds[0], usersIds[0]);
        Assert.assertNotNull(guildGameManagerSteps.getLobbyByUser(usersIds[0]));
    }

    @Test
    public void getLobbyByNonExistingUser() {
        guildGameManagerSteps.addLobby(ownersIds[0], "Lobby 1");
        guildGameManagerSteps.addUser(ownersIds[0], usersIds[0]);
        Assert.assertNull(guildGameManagerSteps.getLobbyByUser(usersIds[1]));
    }

    @After
    public void tearDownTest(){
        guildGameManagerSteps.removeAllLobies();
    }
}
