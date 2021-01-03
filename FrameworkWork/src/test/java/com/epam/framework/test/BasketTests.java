package com.epam.framework.test;

import com.epam.framework.model.Product;
import com.epam.framework.page.ProductPage;
import com.epam.framework.service.ProductCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasketTests extends CommonConditions{
    private Product product = ProductCreator.withUrlAndCodeFromProperty();

    @Test
    public void addProductOnBasket() {
        Boolean addingToBasketOperationWorks = new ProductPage(driver, product.getUrl())
                .openPage()
                .addToBasket()
                .openBasket()
                .isProductOnBasket(product.getCode());

        Assert.assertTrue(addingToBasketOperationWorks);
    }

    @Test
    public void deleteFromBasket(){
        Boolean deletingFromBasketOperationWorks = !new ProductPage(driver, product.getUrl())
                .openPage()
                .addToBasket()
                .openBasket()
                .deleteProductFromBasket()
                .isProductOnBasket(product.getCode());

        Assert.assertTrue(deletingFromBasketOperationWorks);
    }
}
