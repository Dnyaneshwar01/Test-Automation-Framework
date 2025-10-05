package com.ui.pages;

import com.constants.Browser;

import static com.constants.Env.*;

import com.utility.BrowserUtility;

import static com.utility.PropertiesUtils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BrowserUtility {

    private static final By SIGNIN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

    public HomePage(Browser browser, boolean isHeadless) {
        super(browser,isHeadless);

        goToWebsite(readProperty(QA, "URL"));
        maximizeWindow();
    }

    /*
    public HomePage(Browser browser, boolean isHeadless) {
        super(browser,isHeadless);
        goToWebsite(JSONUtility.readJSON(QA));
        maximizeWindow();
    } */

    public HomePage(WebDriver driver){
        super(driver);
        goToWebsite(readProperty(QA, "URL"));
        maximizeWindow();
    }

    public LoginPage goToLoginPage() {
        clickOn(SIGNIN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }
}
