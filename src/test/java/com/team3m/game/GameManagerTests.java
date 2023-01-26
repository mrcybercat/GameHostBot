package com.team3m.game;

import com.team3m.game.steps.GameManagerSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GameManagerTests {
    @Rule
    public SpringIntegrationMethodRule springMethodIntegration = new SpringIntegrationMethodRule();

    @Steps
    GameManagerSteps gameManagerSteps;

    String guildId = "1234567";

    @Before
    public void setUp() {
        gameManagerSteps.addGuildManger(guildId);
    }


    @Test
    public void getGuildManagerExistingId() {
        Assert.assertNotNull(gameManagerSteps.findGuildManager(guildId));
    }

    @Test
    public void getGuildManagerNotExistingId() {
        Assert.assertNull(gameManagerSteps.findGuildManager("7654321"));
    }

}
