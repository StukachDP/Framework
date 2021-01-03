package com.epam.framework.page;

import com.epam.framework.wait.CustomWaits;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String BASKET_PAGE_URL = "https://www.21vek.by/order/";

    private By deleteFromBasketButtonLocator = By.id("j-delete-7191763");

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public BasketPage deleteProductFromBasket(){
        WebElement deleteFromBasketButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(deleteFromBasketButtonLocator));
        deleteFromBasketButton.click();
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }

    public Boolean isProductOnBasket(String productCode){
        By productLocator = By.xpath("//*[@id=\"j-basket__items\"]//*[text()='код " + productCode + "']");
        try{
            new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(productLocator));
        }catch (TimeoutException e){
            logger.info("Product not found");
            return false;
        }
        return true;
    }

    @Override
    public BasketPage openPage() {
        driver.navigate().to(BASKET_PAGE_URL);
        logger.info("Basket page opened");
        CustomWaits.waitForPageLoaded(driver);
        return this;
    }
}
