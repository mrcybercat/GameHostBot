package com.team3m.acceptance.steps;

import com.team3m.acceptance.pages.LoginPage;
import com.team3m.acceptance.pages.MainPage;
import com.team3m.acceptance.pages.TestGuildPage;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class AcceptanceSteps {

    List<WebDriver> drivers = new ArrayList<>();
    LoginPage loginPage;
    MainPage mainPage;
    TestGuildPage testGuildPage;


    @Step("Test agent opens site")
    public void setUpDriver(String targetUrl, String webdriverPath) {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", webdriverPath);
        driver = new ChromeDriver();
        driver.get(targetUrl);
        drivers.add(driver);
    }

    @Step("Test agent logs in")
    public void logIn(String email, String password, Integer agentIndex) {
        loginPage = new LoginPage(drivers.get(agentIndex));
        loginPage.performLogin(email, password);
    }


    @Step("Test agent enters guild")
    public void enterGuild(String guild, Integer agentIndex) {
        mainPage = new MainPage(drivers.get(agentIndex), guild);
        mainPage.enterTestGuild(guild);
    }

    @Step("Test agent uses join command")
    public void performJoinCommand(Integer agentIndex) {
        testGuildPage = new TestGuildPage(drivers.get(agentIndex));
        testGuildPage.sendCommandNoOptions("join");
    }

    @Step("Test agent evaluates if message is as expected")
    public boolean isExpectedEmbedValuePresent(String expectedEmbedValue, Integer agentIndex) {
        testGuildPage = new TestGuildPage(drivers.get(agentIndex));
        boolean embedValueFound = false;
        List<WebElement> webElements = drivers.get(agentIndex).findElements(By.xpath("//*[contains(@class, 'embedFieldValue')]"));
        for (WebElement webElement: webElements) {
            if(webElement.getText().equals(expectedEmbedValue)){
                embedValueFound = true;
            }
        }
        testGuildPage.dismissCommand();
        return embedValueFound;
    }

    @Step("Test agent uses create command")
    public void performCreateCommand(String lobbyName, Integer agentIndex) {
        testGuildPage = new TestGuildPage(drivers.get(agentIndex));
        testGuildPage.sendCommandWithOptions("create", new String[]{lobbyName});
        testGuildPage.pressConfirmButton();
    }

    @Step("Test agent chooses lobby")
    public void chooseLobbyToJoin(String lobbyToJoin, Integer agentIndex) {
        testGuildPage = new TestGuildPage(drivers.get(agentIndex));
        testGuildPage.activateSelectMenu();
        List<WebElement> webElements = drivers.get(agentIndex).findElements(By.xpath("//*[contains(@class, 'selectOption')]"));
        for (WebElement webElement: webElements) {
            if(webElement.getText().equals(lobbyToJoin)){
                webElement.click();
            }
        }
    }
}