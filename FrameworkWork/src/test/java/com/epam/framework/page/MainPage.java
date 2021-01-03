package com.epam.framework.page;

import com.epam.framework.model.User;
import com.epam.framework.wait.CustomWaits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String MAIN_URL = "https://www.21vek.by/";

    @FindBy(className = "userToolsToggler")
    private WebElement userAccountButton;

    @FindBy(id = "selen-7anxb49cjn")
    private WebElement signInFormButton;

    @FindBy(className = "selen-pokdbn5820x")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@name='email']")
    private WebElement inputLoginField;

    @FindBy(xpath = "//*[@name='password']")
    private WebElement inputPasswordField;

    @FindBy(xpath = "//*[text()='Выход']")
    private WebElement signOutButton;

    private By currentUserEmailLocator = By.className("userToolsSubtitle");
    private By popUpElement = By.id("react-popup");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(MAIN_URL);
        logger.info("Main page opened");
        return this;
    }

    public MainPage openUserTools(){
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(userAccountButton));
            userAccountButton.click();
        } catch(org.openqa.selenium.StaleElementReferenceException exception) {
            userAccountButton.click();
        }
        return this;
    }

    public MainPage openSignInForm(){
        signInFormButton.click();
        return this;
    }

    public MainPage signIn(User user){
        openUserTools();
        openSignInForm();
        inputSignInInformationForm(user);
        signInButton.click();
        logger.info("User logged in");
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.attributeToBe(popUpElement, "display", "none"));
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public MainPage inputSignInInformationForm(User user){
        inputLoginField.sendKeys(user.getEmail());
        inputPasswordField.sendKeys(user.getPassword());
        return this;
    }

    public String getAuthorizedUserEmail(){
        openUserTools();
        WebElement currentUserEmail = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(currentUserEmailLocator));
        return currentUserEmail.getText();
    }

}
