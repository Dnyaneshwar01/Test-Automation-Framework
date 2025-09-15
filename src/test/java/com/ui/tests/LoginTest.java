package com.ui.tests;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.Browser.*;

@Listeners({com.ui.listerners.TestListener.class})
public class LoginTest extends TestBase {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Test(description = "Verified if the valid user is able to login into the application",
            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
            dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        logger.info("Logging in with: " + user.getEmailAddress());
        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
        Assert.assertEquals(userName, "Sachin Sharma");
    }

//    @Test(description = "Verified if the valid user is able to login into the application",
//            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
//            dataProvider = "LoginTestCSVDataProvider",
//            retryAnalyzer = com.ui.listerners.MyRetryAnalyzer.class)
//    public void loginCSVTest(User user) {
//        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
//        Assert.assertEquals(userName, "Sachin Sharma");
//    }


//    @Test(description = "Verified if the valid user is able to login into the application",
//            dataProviderClass = com.ui.dataProviders.LoginDataProvider.class,
//            dataProvider = "LoginTestExcelDataProvider",
//            retryAnalyzer = com.ui.listerners.MyRetryAnalyzer.class)
//    public void loginExcelTest(User user) {
//        System.out.println("Logging in with: " + user.getEmailAddress());
//        String userName = homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
//        Assert.assertEquals(userName, "Sachin Sharma");
//    }

}
