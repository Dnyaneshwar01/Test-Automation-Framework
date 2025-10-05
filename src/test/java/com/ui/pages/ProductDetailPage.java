package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends BrowserUtility {

    private static final By SELECT_DRESS_SIZE               = By.cssSelector("select[class='form-control attribute_select no-print']");
    private static final By WHITE_DRESS_COLOR               = By.cssSelector("a[title='White']");
    private static final By ADD_TO_CARD_BUTTON              = By.cssSelector("p[id='add_to_cart'] button[type='submit']");
    private static final By PROCCED_TO_CHECKOUT_BUTTON      = By.cssSelector("a[title='Proceed to checkout']");

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailPage changeSize(String size){
        selectFormDropdown(SELECT_DRESS_SIZE,size);
        return new ProductDetailPage(getDriver());
    }

    public ProductDetailPage changeProductColor(String color){
        clickOn(WHITE_DRESS_COLOR);
        return new ProductDetailPage(getDriver());
    }

    public ProductDetailPage addProductToCart() {
        waitUntilElementDisplay(ADD_TO_CARD_BUTTON);
        clickOn(ADD_TO_CARD_BUTTON);
        return new ProductDetailPage(getDriver());
    }

    public ShopingCardPage proccedToCheckout() {
        waitUntilElementDisplay(PROCCED_TO_CHECKOUT_BUTTON);
        clickOn(PROCCED_TO_CHECKOUT_BUTTON);
         return new ShopingCardPage(getDriver());
    }

}
