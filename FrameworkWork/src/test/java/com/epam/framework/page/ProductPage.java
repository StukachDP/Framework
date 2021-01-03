package com.epam.framework.page;


import com.epam.framework.wait.CustomWaits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private String productUrl;

    @FindBy(xpath = "//*[@class='headerCart']/a")
    private WebElement basketButton;

    @FindBy(xpath = "//button[text()='В корзину']")
    private WebElement addToBasketButton;


    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductPage(WebDriver driver, String productUrl) {
        super(driver);
        this.productUrl = productUrl;
        PageFactory.initElements(this.driver, this);
    }

    public ProductPage addToBasket(){
        addToBasketButton.click();
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public BasketPage openBasket(){
        basketButton.click();
        logger.info("Cart page opened");
        CustomWaits.waitForPageLoaded(driver);
        return new BasketPage(driver);
    }

    @Override
    public ProductPage openPage() {
        driver.navigate().to(productUrl);
        logger.info("Product page opened");
        return this;
    }
}
