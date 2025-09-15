package com.utility;

import com.constants.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        this.driver.set(driver);
    }

    public BrowserUtility(Browser browserName) {
        if (browserName == Browser.CHROME)
            driver.set(new ChromeDriver());
        else if (browserName == Browser.EDGE)
            driver.set(new EdgeDriver());
        else if (browserName == Browser.FIREFOX)
            driver.set(new FirefoxDriver());
    }

    public BrowserUtility(Browser browserName,boolean isHeadless) {
        if (browserName == Browser.CHROME) {
            if(isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }else{
                driver.set(new ChromeDriver());
            }
        }
        else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless");
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
            } else {
                driver.set(new EdgeDriver());
            }
        }

        else if (browserName == Browser.FIREFOX) {
            if (isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                driver.set(new FirefoxDriver(options));
            }else {
                driver.set(new FirefoxDriver());
            }
        }
    }

    public void goToWebsite(String url) {
        driver.get().get(url);
    }

    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        WebElement element = driver.get().findElement(locator);
        element.click();
    }

    public void enterText(By locator, String textToBeEnter) {
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(textToBeEnter);
    }

    public String getVisibleText(By locator) {
        WebElement element = driver.get().findElement(locator);
        return element.getText();
    }

    public String takeScreenshot(String name)  {
        TakesScreenshot screenshot= (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = simpleDateFormat.format(date);
        String path = System.getProperty("user.dir") + "//Screenshot//" + name +" - " + timeStamp +".png";
        try {
            FileHandler.copy(screenshotData , new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void quit() {
        driver.get().quit();
    }
}
