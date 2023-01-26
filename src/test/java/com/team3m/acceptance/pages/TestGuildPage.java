package com.team3m.acceptance.pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class TestGuildPage {
    WebDriver driver;

    @FindBy(xpath = "//*[contains(@role, 'textbox')]")
    WebElement textField;

    @FindBy(xpath = "//*[contains(@class, 'autocompleteRowHeading')]")
    WebElement commandHintPopUp;

    @FindBy(xpath = "//*[contains(@class, 'anchorUnderlineOnHover') and contains(@tabindex, '0')]")
    WebElement dismissEphemeralButton;

    @FindBy(xpath = "//*[contains(@class, 'colorGreen')]")
    WebElement confirmButton;

    @FindBy(xpath = "//*[contains(@class, 'colorPrimary')]")
    WebElement discardButton;

    @FindBy(xpath = "//*[contains(@class, 'select')]")
    WebElement selectMenu;

    public TestGuildPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendCommandNoOptions(String command){
        textField.sendKeys("/" + command);
        commandHintPopUp.click();
        textField.click();
        textField.sendKeys(Keys.RETURN);
    }

    public void sendCommandWithOptions(String command, String[] options){
        textField.sendKeys("/" + command);
        commandHintPopUp.click();
        for (String option: options) {
            textField.sendKeys(option);
            textField.click();
            textField.sendKeys(Keys.ENTER);
        }
        textField.click();
        textField.sendKeys(Keys.ENTER);
    }
    public void dismissCommand(){
        dismissEphemeralButton.click();
    }

    public void pressConfirmButton(){
        confirmButton.click();
    }

    public void pressDiscardButton(){
        discardButton.click();
    }

    public void activateSelectMenu(){
        selectMenu.click();
    }

}
//textField.sendKeys(Keys.LEFT_CONTROL, Keys.RETURN);
//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//textField.sendKeys("\n");
//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        System.out.println("commandHintPopUp.getText() " + commandHintPopUp.getText() + " ");
