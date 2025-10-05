package com.ui.tests;

import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listerners.TestListener.class})
public class InvalidCredLoginTest extends TestBase {

    private static String INVALID_EMAIL_ADDRESS= "invalidtest34@gmail.com";
    private static String INVALID_PASSWORD = "pass@123";


    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Test(description = "Verified the proper validation message disply or not when they enter the invalid credential",
            groups = {"e2e","smoke","sanity"})
    public void loginWithInvalidCred() {
        String authErrorMessage = homePage.goToLoginPage().doLoginWIthInvalideCredential(INVALID_EMAIL_ADDRESS,INVALID_PASSWORD).getErrorMessage();
        Assert.assertEquals(authErrorMessage,"Authentication failed.");
    }
}
