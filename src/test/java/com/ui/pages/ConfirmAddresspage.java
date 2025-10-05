package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmAddresspage extends BrowserUtility {

    private static final By PROCCED_TO_CHECKOUT = By.cssSelector("button[name='processAddress']");

    public ConfirmAddresspage(WebDriver driver){
        super(driver);
    }

    public shippmentPage goTOShipppmentPage() {
        clickOn(PROCCED_TO_CHECKOUT);
        return new shippmentPage(getDriver());
    }
}
