package com.ui.tests;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddFirstAddressTest extends TestBase {

    private MyAccountPage myAccountPage;
    private AddressPage addressPage;
    private AddressPOJO address;

    @BeforeMethod(description = "Valid user login into the application and not add address")
    public void setup() {
        myAccountPage = homePage.goToLoginPage().doLoginWith("sdetdnuser01@yopmail.com","Password");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(description = "Adding and validating new address")
    public void addNewAddress() {
        myAccountPage.goToAddAddressPage().saveAddress(address);
        addressPage.verifyAddNewAddressButtonDisplay();
    }
}
