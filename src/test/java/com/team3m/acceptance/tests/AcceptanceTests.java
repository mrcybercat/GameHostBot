package com.team3m.acceptance.tests;


import com.team3m.acceptance.steps.AcceptanceSteps;
import com.team3m.bot.GameHostBotApp;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.thucydides.core.annotations.Steps;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(SerenityRunner.class)
@SpringBootTest(classes = GameHostBotApp.class)
public class AcceptanceTests {

    @Rule
    public SpringIntegrationMethodRule springMethodIntegration = new SpringIntegrationMethodRule();

    @Steps
    AcceptanceSteps acceptanceSteps;

    @Value("${selenium.webdriver}")
    private String webdriverPath;

    @Value("${selenium.target-guild}")
    private String guild;

    @Value("${selenium.test-agent-email-1}")
    private String agent1email;

    @Value("${selenium.test-agent-password-1}")
    private String agent1password;

    @Value("${selenium.test-agent-email-2}")
    private String agent2email;

    @Value("${selenium.test-agent-password-2}")
    private String agent2password;

    @Value("${selenium.test-agent-email-3}")
    private String agent3email;

    @Value("${selenium.test-agent-password-3}")
    private String agent3password;

    String targetUrl = "https://discord.com/login";

    String lobbyName = "Lobby Uno";


    @Before
    public void setUp() {
        setUpTestAgent(agent1email, agent1password, 0);
        setUpTestAgent(agent2email, agent2password, 1);
        setUpTestAgent(agent3email, agent3password, 2);
    }

    @Test
    public void joinLobbyNoLobbies() {
        acceptanceSteps.performJoinCommand(0);
        Assert.assertTrue(acceptanceSteps.isExpectedEmbedValuePresent("There are no lobbies to join sorry", 0));
    }

    @Test
    public void joinLobbyOwnLobby() {
        acceptanceSteps.performCreateCommand(lobbyName, 0);
        acceptanceSteps.performJoinCommand(0);
        acceptanceSteps.chooseLobbyToJoin(lobbyName,0);
        Assert.assertTrue(acceptanceSteps.isExpectedEmbedValuePresent("You can not join lobby twice", 0));
    }

    @Test
    public void joinLobbyDifferentLobby() {
        acceptanceSteps.performCreateCommand(lobbyName, 0);
        acceptanceSteps.performJoinCommand(1);
        acceptanceSteps.chooseLobbyToJoin(lobbyName, 1);
        Assert.assertTrue(acceptanceSteps.isExpectedEmbedValuePresent("Connected to " + lobbyName, 1));
    }


    private void setUpTestAgent(String agentEmail, String agentPassword, Integer agentIndex){
        acceptanceSteps.setUpDriver(targetUrl, webdriverPath);
        acceptanceSteps.logIn(agentEmail, agentPassword, agentIndex);
        acceptanceSteps.enterGuild(guild, agentIndex);
    }

    @After
    public void tearDown(){
        acceptanceSteps.tearDownDriver();
    }

}


//    @Value("${selenium.target-guild}")
//    private String guild;
//    @Value("${selenium.test-agent-2}")/
//   private String agent2;
//  @Value("${selenium.test-agent-3}")
//    private String agent3;