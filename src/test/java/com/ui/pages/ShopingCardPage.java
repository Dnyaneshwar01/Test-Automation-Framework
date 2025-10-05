package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopingCardPage extends BrowserUtility {
    private static final By PROCCED_CHECKOUT_BUTTON         = By.cssSelector("a[class*='checkout '][title='Proceed to checkout']");

    public ShopingCardPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmAddresspage goTOConfirmAddressPage(){
        waitUntilElementDisplay(PROCCED_CHECKOUT_BUTTON);
        clickOn(PROCCED_CHECKOUT_BUTTON);
        return new ConfirmAddresspage(getDriver());
    }


}
