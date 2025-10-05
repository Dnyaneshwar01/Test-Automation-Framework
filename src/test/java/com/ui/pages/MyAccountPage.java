package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR              = By.cssSelector("a[title='View my customer account'] span");
    private static final By SEARCH_TEXT_BOX_LOCATOR        = By.cssSelector("input[id='search_query_top']");
    private static final By ADD_NEW_ADDRESS_LINK_LOCATOR   = By.cssSelector("a[title='Add my first address']");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        return getVisibleText(USER_NAME_LOCATOR);
    }

    public SearchResultPage searchForAProduct(String productName) {
        enterText(SEARCH_TEXT_BOX_LOCATOR,productName);
        enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
        SearchResultPage searchResultPage = new SearchResultPage(getDriver());
        return searchResultPage;
    }

    public AddressPage goToAddAddressPage() {
        waitUntilElementDisplay(ADD_NEW_ADDRESS_LINK_LOCATOR);
        clickOn(ADD_NEW_ADDRESS_LINK_LOCATOR);
        AddressPage addressPage = new AddressPage(getDriver());
        return addressPage;
    }
}
