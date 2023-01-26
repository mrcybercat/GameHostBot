package com.team3m.acceptance.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@name, 'email')]")
    WebElement emailField;

    @FindBy(xpath = "//*[contains(@name, 'password')]")
    WebElement passwordField;

    @FindBy(xpath = "//*[contains(@type, 'submit')]")
    WebElement loginButton;



    public void performLogin(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}



//  By emailField  = By.xpath("//*[contains(@name, 'email')]");
// By passwordField = By.xpath("//*[contains(@name, 'password')]");
//  By loginButton = By.xpath("//*[contains(@type, 'submit')]");
