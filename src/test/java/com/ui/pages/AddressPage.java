package com.ui.pages;

import com.ui.pojo.AddressPOJO;
import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.print.attribute.standard.MediaSize;

public class AddressPage extends BrowserUtility {

    private static final By COMPANY_TEXTBOX_LOCATOR              = By.cssSelector("input[id='company']");
    private static final By ADDRESS1_TEXTBOX_LOCATOR             = By.cssSelector("input[id='address1']");
    private static final By ADDRESS2_TEXTBOX_LOCATOR             = By.cssSelector("input[id='address2']");
    private static final By CITY_TEXTBOX_LOCATOR                 = By.cssSelector("input[id='city']");
    private static final By POST_CODE_TEXTBOX_LOCATOR            = By.cssSelector("input[id='postcode']");
    private static final By HOME_PHONE_TEXTBOX_LOCATOR           = By.cssSelector("input[id='phone']");
    private static final By MOBILE_NUMBER_TEXTBOX_LOCATOR        = By.cssSelector("input[id='phone_mobile']");
    private static final By OTHER_INFORMATION_TEXTAREA_LOCATOR   = By.cssSelector("textarea[id='other']");
    private static final By ADDRESS_ALTIAS_TEXTBOX_LOCATOR       = By.cssSelector("input[id='alias']");
    private static final By STATE_DROPDOWN_LOCATOR               = By.cssSelector("select[id='id_state']");
    private static final By SAVE_ADDRESS_LOCATOR                 = By.cssSelector("button[id='submitAddress']");
    private static final By ADD_NEW_ADDRESS                      = By.cssSelector("a[title='Add an address']");

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public void saveAddress(AddressPOJO addressPOJO) {
        enterText(COMPANY_TEXTBOX_LOCATOR,addressPOJO.getCompany());
        enterText(ADDRESS1_TEXTBOX_LOCATOR, addressPOJO.getAddressLine1());
        enterText(ADDRESS2_TEXTBOX_LOCATOR, addressPOJO.getAddressLine2());
        enterText(CITY_TEXTBOX_LOCATOR,addressPOJO.getCity());
        enterText(POST_CODE_TEXTBOX_LOCATOR,addressPOJO.getPostCode());
        enterText(HOME_PHONE_TEXTBOX_LOCATOR,addressPOJO.getHomePhone());
        enterText(MOBILE_NUMBER_TEXTBOX_LOCATOR,addressPOJO.getMobileNumber());
        selectFormDropdown(STATE_DROPDOWN_LOCATOR, addressPOJO.getState());
        waitUntilElementDisplay(OTHER_INFORMATION_TEXTAREA_LOCATOR);
        enterText(OTHER_INFORMATION_TEXTAREA_LOCATOR,addressPOJO.getOtherInformation());
        enterText(ADDRESS_ALTIAS_TEXTBOX_LOCATOR,addressPOJO.getAddressAltias());
        waitUntilElementDisplay(SAVE_ADDRESS_LOCATOR);
        clickOn(SAVE_ADDRESS_LOCATOR);
    }

    public void verifyAddNewAddressButtonDisplay(){
        waitUntilElementDisplay(ADD_NEW_ADDRESS);
        Assert.assertTrue(isElementDisplayed(ADD_NEW_ADDRESS));
    }

}
