package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BrowserUtility {

    private static final By PAY_BY_BANK_WIRE_LOCATOR                = By.cssSelector("a[title='Pay by bank wire']");
    private static final By CONFIRM_ORDER_BUTTON_LOCATOR            = By.cssSelector("button[class='button btn btn-default button-medium']");
    private static final By ALERT_SUCCESS_MSG_LOCATOR               = By.cssSelector("p[class='alert alert-success']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public String makePaymentByWire() {;
        clickOn(PAY_BY_BANK_WIRE_LOCATOR);
        clickOn(CONFIRM_ORDER_BUTTON_LOCATOR);
        waitUntilElementDisplay(ALERT_SUCCESS_MSG_LOCATOR);
         return getVisibleText(ALERT_SUCCESS_MSG_LOCATOR);
    }
}
