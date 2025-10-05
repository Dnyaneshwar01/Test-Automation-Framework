package com.ui.tests;


import com.ui.pages.MyAccountPage;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listerners.TestListener.class})
public class SearchProductTest extends TestBase {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    private MyAccountPage myAccountPage;
    private final String SEARCH_TERM = "Printed Summer Dress";
    @BeforeMethod(description = "Valid user login into the application")
    public void setup() {
        myAccountPage = homePage.goToLoginPage().doLoginWith("sdetdnuser01@yopmail.com","Password");

    }

    @Test(description = "Verify if the logged in user is able to search for a product and correct product search diplay",
           groups = {"e2e","smoke","sanity"})
    public void verifyProductSearchTest() {
        boolean actualResult = myAccountPage.searchForAProduct(SEARCH_TERM)
                .isSearchTermPresentInProductList(SEARCH_TERM);
        Assert.assertTrue(actualResult);
    }

}
