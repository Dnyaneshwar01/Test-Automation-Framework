package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.cssSelector("a[title='View my customer account'] span");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return getVisibleText(USER_NAME_LOCATOR);
    }


}
