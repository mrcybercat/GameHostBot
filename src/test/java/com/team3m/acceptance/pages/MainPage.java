package com.team3m.acceptance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver, String targetGuild) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void enterTestGuild(String targetGuild){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement testGuildButton = driver.findElement(By.xpath("//*[contains(@aria-label, ' "+ targetGuild +"')]"));
        testGuildButton.click();
    }

}
