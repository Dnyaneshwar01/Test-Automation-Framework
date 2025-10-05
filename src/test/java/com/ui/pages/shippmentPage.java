package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class shippmentPage extends BrowserUtility {
    private static final By CHK_TERMS_AND_SERVICES          = By.cssSelector("input[type='checkbox']");
    private static final By PROCCED_CHECKOUT                = By.cssSelector("button[name='processCarrier']");

    public shippmentPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage gotoPaymentPage() {
        clickOn(CHK_TERMS_AND_SERVICES);
        clickOn(PROCCED_CHECKOUT);
        return new PaymentPage(getDriver());
    }

}
