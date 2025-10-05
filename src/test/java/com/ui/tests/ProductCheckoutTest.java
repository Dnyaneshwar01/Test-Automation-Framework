package com.ui.tests;

import com.ui.pages.SearchResultPage;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class ProductCheckoutTest extends TestBase {

    private static final String SEARCH_TERM = "Printed summer dress";
    private SearchResultPage searchResultPage;

    @BeforeMethod (description = "User login into the application and searches a product")
    public void setUp() {
        searchResultPage = homePage.goToLoginPage().doLoginWith("sdetdnuser01@yopmail.com","Password")
                .searchForAProduct(SEARCH_TERM);
    }

    @Test (description = "Verify if the logged in user is able to buy a product")
    public void checkoutTest() {
       String result = searchResultPage.clickOnTheProduct().changeSize("L").changeProductColor("White")
               .addProductToCart().proccedToCheckout().goTOConfirmAddressPage()
               .goTOShipppmentPage().gotoPaymentPage().makePaymentByWire();
        logger.info("Order place message:: "+ result);
        Assert.assertTrue(result.contains("Your order on My Shop is complete"));
    }
}
