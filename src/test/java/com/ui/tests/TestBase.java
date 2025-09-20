package com.ui.tests;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static com.constants.Browser.CHROME;

public class TestBase {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private boolean isLambdaTest = true;

    @Parameters({"browser","isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Load the Homepage of the website")
    public void setup(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLambdaTest,
            @Optional("false") boolean isHeadless,
            ITestResult result) {

        this.isLambdaTest = isLambdaTest;
        WebDriver lambdaDriver;

        if(isLambdaTest) {
            lambdaDriver = LambdaTestUtility.initialzeLambdaTestSession(browser,result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        }
        else {
            logger.info("Load home page for the website");
            homePage = new HomePage(CHROME, isHeadless);
        }
    }

    public BrowserUtility getInstance() {
        return homePage;
    }

    @AfterClass(description = "Tear Down the browser")
    public void teardown() {
        if(isLambdaTest) {
            LambdaTestUtility.quiteSession();  //Quit or close the browser session of LT
        }else{
            homePage.quit(); // local
        }

    }
}
